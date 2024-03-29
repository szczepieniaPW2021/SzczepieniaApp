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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.database.model.PatientEntity
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.SearchPatientFragment
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

    private val _persons = MutableLiveData<PatientEntity>()
    val persons: LiveData<PatientEntity> get() = _persons

    private val _vaccineDoses = MutableLiveData<ArrayList<String>>()
    val vaccineDoses: LiveData<ArrayList<String>> get() = _vaccineDoses

    private val _vaccineTypes = MutableLiveData<ArrayList<String>>()
    val vaccineTypes: LiveData<ArrayList<String>> get() = _vaccineTypes

    private val _patientSearchLoading = MutableLiveData<Boolean>()
    val patientSearchLoading: LiveData<Boolean> get() = _patientSearchLoading

    var lotNumber = ""
    private var vaccineDose = ""
    private var vaccineType = ""

    private val disposable = CompositeDisposable()

    init {
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
            val name = string.substringBefore(" ")
            var lastName = string.substringAfter(" ")
            getPatientByName(name = name, lastName = lastName)
        }
    }

    private fun getPatientByIdNumber(idNumber: Long) {
        _patientSearchLoading.postValue(true)
        GlobalScope.launch {
            val result = useCaseFactory.getPatientByIdNumberUseCase.execute(idNumber = idNumber)
            _persons.postValue(result)
            _patientSearchLoading.postValue(false)
        }
    }

    private fun getPatientByName(name: String, lastName: String) {
        _patientSearchLoading.postValue(true)
        GlobalScope.launch {
            val result = useCaseFactory.getPatientByNameUseCase.execute(name = name, lastName = lastName)
            _persons.postValue(result)
            _patientSearchLoading.postValue(false)
        }
    }

    fun getPatientName() : String {

        Log.d("testuje", "getPatientName: " + persons.value?.name + " " + persons.value?.lastName)

        return context.value!!.resources.getString(R.string.search_patient_fragment_patient_text).format(persons.value?.name, persons.value?.lastName)
    }

    fun getPatientPesel() : String {
        return context.value!!.resources.getString(R.string.search_patient_fragment_patient_pesel_text).format(persons.value?.personalNumber)
    }

    fun registerVaccination(view: View) {
        Log.d(SearchPatientViewModel::class.java.name, "Vaccination is being registered." )
        if (vaccineDose.isNullOrEmpty() || vaccineType.isNullOrEmpty() || lotNumber.isNullOrEmpty()) {
            callback.toastMessage(view.context, view.context.resources.getString(R.string.search_patient_fragment_vaccination_register_data_not_complete_text))
            return
        }

        callback.setDialog(view, view.context.resources.getString(R.string.search_patient_fragment_vaccination_is_being_registered_text))
        val time = System.currentTimeMillis()

        disposable.add(
            useCaseFactory.registerVaccinationUseCase.execute(
                persons.value!!,
                vaccineDose,
                vaccineType,
                createStringForQRCode(persons.value!!.personalNumber!!, time, vaccineDose, lotNumber, vaccineType),
                time
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableCompletableObserver(){
                    override fun onComplete() {
                        callback.dismissDialog()
                        callback.toastMessage(view.context, view.context.resources.getString(R.string.search_patient_fragment_vaccination_registered_text))
                        Navigation.findNavController(view).navigate(R.id.action_searchPatientFragment_to_doctorFragment)
                    }

                    override fun onError(e: Throwable) {
                        callback.dismissDialog()
                    }
                })
        )
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
            "Wybierz dawkę:" -> { "" }
            else -> { item }
        }
    }

    private fun selectVaccineType(item: String, view: View?) {
        vaccineType = when (item) {
            "Wybierz szczepionkę:" -> { "" }
            else -> { item }
        }
    }

    private fun createStringForQRCode(idNumber: Long, time: Long, dose: String, lotNumber: String, vaccineType: String): String? {
        return if (dose == "Dawka 2") "$idNumber/$time/$lotNumber/$vaccineType" else null
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}