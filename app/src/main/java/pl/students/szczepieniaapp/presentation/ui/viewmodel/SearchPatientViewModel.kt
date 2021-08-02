package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.SearchPatientFragment
import pl.students.szczepieniaapp.presentation.util.EspressoIdlingResource
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.*
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

    private val _patientSearchLoading = MutableLiveData<Boolean>()
    val patientSearchLoading: LiveData<Boolean> get() = _patientSearchLoading

    var lotNumber = ""
    private var vaccineDose = ""
    private var vaccineType = ""

    init{
        fetchVaccineDose()
        fetchVaccineType()
    }

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
                Log.d(SearchPatientViewModel::class.java.name, "isOnlyNumeric is searching by id number}")
                getPatientByIdNumber(string.toLong())
            }
        } else {
            Log.d(SearchPatientViewModel::class.java.name, "isOnlyNumeric is searching by name")
            getPatientByName(string)
        }
    }

    private fun getPatientByIdNumber(idNumber: Long) {

        useCaseFactory.getPatientByIdNumberUseCase
            .execute(idNumber = idNumber)
            .onEach { dataState ->

                _patientSearchLoading.postValue(dataState.loading)

                dataState.data?.let {patient ->
                    _persons.postValue(patient)
                }

                dataState.error?.let { error ->
                    Log.e(SearchPatientViewModel::class.java.simpleName, "getPatientByIdNumber: $error")
                }

            }.launchIn(GlobalScope)
    }

    private fun getPatientByName(name: String) {

        useCaseFactory.getPatientByNameUseCase
            .execute(name = name)
            .onEach { dataState ->

                _patientSearchLoading.postValue(dataState.loading)

                dataState.data?.let {patient ->
                    _persons.postValue(patient)

                }

                dataState.error?.let { error ->
                    Log.e(SearchPatientViewModel::class.java.simpleName, "getPatientByName: $error")
                }

            }.launchIn(GlobalScope)
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

    private fun fetchVaccineDose() {

        useCaseFactory.getVaccineDoseUseCase
            .execute()
            .onEach { dataState ->

                dataState.data?.let {vaccineDoses ->
                    _vaccineDoses.postValue(vaccineDoses)
                }

                dataState.error?.let { error ->
                    Log.e(SearchPatientViewModel::class.java.simpleName, "fetchVaccineDose: $error")
                }

            }.launchIn(viewModelScope)

    }

    private fun fetchVaccineType() {

        useCaseFactory.getVaccineTypeUseCase
            .execute()
            .onEach { dataState ->

                dataState.data?.let {vaccineTypes ->
                    _vaccineTypes.postValue(vaccineTypes)
                }

                dataState.error?.let { error ->
                    Log.e(SearchPatientViewModel::class.java.simpleName, "fetchVaccineType: $error")
                }

            }.launchIn(viewModelScope)

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