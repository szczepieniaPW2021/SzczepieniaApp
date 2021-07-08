package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.LoginFragment
import pl.students.szczepieniaapp.presentation.ui.fragment.SearchPatientFragment
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.*
import java.util.Map
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SearchPatientViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel(), SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener {

    private var callback: SearchPatientFragment = SearchPatientFragment()

    private val _persons = MutableLiveData<List<String>>()
    val persons: LiveData<List<String>> get() = _persons

    private val _vaccineDoses = MutableLiveData<ArrayList<String>>()
    val vaccineDoses: LiveData<ArrayList<String>> get() = _vaccineDoses

    private val _vaccineTypes = MutableLiveData<ArrayList<String>>()
    val vaccineTypes: LiveData<ArrayList<String>> get() = _vaccineTypes

    var lotNumber = ""
    private var vaccineDose = ""
    private var vaccineType = ""

    override fun onQueryTextSubmit(query: String?): Boolean {
        isOnlyNumeric(query)
        Log.d(SearchPatientViewModel::class.java.name, "onQueryTextSubmit: $query")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun isOnlyNumeric(string: String?) {
        if (string!!.matches(Regex("[0-9]+"))) {
            if (string.length != 11) {
                callback.toastMessage(context.value!!, context.value!!.resources.getString(R.string.search_patient_fragment_wrong_pesel_length_text))
            } else {
                Log.d(SearchPatientViewModel::class.java.name, "isOnlyNumeric is searching by pesel: ${getPatientByPesel(string.toLong())}")
                _persons.postValue(getPatientByPesel(string.toLong()))
                _vaccineDoses.postValue(fetchVaccineDose())
                _vaccineTypes.postValue(fetchVaccineType())
            }
        } else {
            Log.d(SearchPatientViewModel::class.java.name, "isOnlyNumeric is searching by name: ${getPatientByName(string)}")
            _persons.postValue(getPatientByName(string))
            _vaccineDoses.postValue(fetchVaccineDose())
            _vaccineTypes.postValue(fetchVaccineType())
        }
    }

    private fun getPatients(): HashMap<Long, String> {
        val patients: HashMap<Long, String> = hashMapOf()
        patients[12345678900L] = "Jan Nowak"
        patients[12345678901L] = "Tomasz Kowalski"
        patients[12345678902L] = "Paweł Podgórski"
        patients[12345678903L] = "Jacek Nowak"
        patients[12345678904L] = "Robert Nowacki"
        patients[12345678905L] = "Mateusz Kowalski"
        patients[12345678906L] = "Szymon Jackowiak"
        patients[12345678907L] = "Tomasz Zięba"
        return patients
    }

    private fun getPatientByPesel(pesel: Long): List<String> {

        var person: MutableList<String> = mutableListOf()

        getPatients()[pesel]?.let {
            person.add(it)
            person.add(pesel.toString())
        }

        return person
    }

    private fun getPatientByName(name: String): List<String> {

        var person: MutableList<String> = mutableListOf()

        getPatients().filterValues { it == name }.keys.let {
            if (it.isNotEmpty()) {
                person.add(name)
                person.add(it.first().toString())
            }
        }

        return person
    }

    fun getPatientName() : String {
        return context.value!!.resources.getString(R.string.search_patient_fragment_patient_text).format(persons.value?.get(0)!!)
    }

    fun getPatientPesel() : String {
        return context.value!!.resources.getString(R.string.search_patient_fragment_patient_pesel_text).format(persons.value?.get(1)!!)
    }

    fun registerVaccination(view: View) {
        Log.d(SearchPatientViewModel::class.java.name, "Vaccination is being registered." )
        if (vaccineDose.isNullOrEmpty() || vaccineType.isNullOrEmpty() || lotNumber.isNullOrEmpty()) {
            callback.toastMessage(view.context, view.context.resources.getString(R.string.search_patient_fragment_vaccination_register_data_not_complete_text))
            return
        }

        GlobalScope.launch(Dispatchers.Main) {
            callback.setDialog(view, view.context.resources.getString(R.string.search_patient_fragment_vaccination_is_being_registered_text))
            delay(2000)
            callback.dismissDialog()
            callback.toastMessage(view.context, view.context.resources.getString(R.string.search_patient_fragment_vaccination_registered_text))
            Navigation.findNavController(view).navigate(R.id.action_searchPatientFragment_to_doctorFragment)
        }
    }

    private fun fetchVaccineDose(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Select dose:")
        data.add("Dose 1")
        data.add("Dose 2")
        return data
    }

    private fun fetchVaccineType(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Select vaccine:")
        data.add("Astra Zeneca")
        data.add("Pfizer/BioNTech")
        data.add("Moderna")
        data.add("Johnson & Johnson")
        return data
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(SearchPatientViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")
        if (parent!!.id == R.id.selectVaccineDoseSpinner) {
            selectVaccineDose(parent!!.adapter.getItem(position) as String, view)
        } else if (parent!!.id == R.id.selectVaccineTypeSpinner) {
            selectVaccineType(parent!!.adapter.getItem(position) as String, view)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(SearchPatientViewModel::class.java.name, "onNothingSelected:")
    }

    private fun selectVaccineDose(item: String, view: View?) {
        vaccineDose = when (item) {
            "Select dose:" -> { "" }
            else -> { item }
        }
    }

    private fun selectVaccineType(item: String, view: View?) {
        vaccineType = when (item) {
            "Select vaccine:" -> { "" }
            else -> { item }
        }
    }
}