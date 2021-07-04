package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : MyViewModel(), SearchView.OnQueryTextListener {

    private var callback: SearchPatientFragment = SearchPatientFragment()

    private val _person = MutableLiveData<List<String>>()
    val person: LiveData<List<String>> get() = _person

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
                _person.postValue(getPatientByPesel(string.toLong()))
            }
        } else {
            Log.d(SearchPatientViewModel::class.java.name, "isOnlyNumeric is searching by name: ${getPatientByName(string)}")
            _person.postValue(getPatientByName(string))
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
        return context.value!!.resources.getString(R.string.search_patient_fragment_patient_text).format(
            person.value?.get(0)!!)
    }

    fun getPatientPesel() : String {
        return context.value!!.resources.getString(R.string.search_patient_fragment_patient_pesel_text).format(
            person.value?.get(1)!!)
    }

    fun registerVaccination(view: View) {
        Log.d(SearchPatientViewModel::class.java.name, "Vaccination is being registered." )
    }
}