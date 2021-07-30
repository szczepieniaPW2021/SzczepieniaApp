package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.CalendarView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.PatientCalendarFragment
import pl.students.szczepieniaapp.presentation.ui.fragment.VisitsDialogFragment
import pl.students.szczepieniaapp.presentation.ui.listener.PatientCalendarListener
import pl.students.szczepieniaapp.presentation.util.EspressoIdlingResource
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class PatientCalendarViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel(), AdapterView.OnItemSelectedListener {

    private var callback: PatientCalendarListener = PatientCalendarFragment()

    private val _cities = MutableLiveData<ArrayList<String>>()
    val cities: LiveData<ArrayList<String>> get() = _cities

    private val _facilities = MutableLiveData<ArrayList<String>>()
    val facilities: LiveData<ArrayList<String>> get() = _facilities

    private val _isFacilitySpinnerVisible = MutableLiveData<Boolean>()
    val isFacilitySpinnerVisible: LiveData<Boolean> get() = _isFacilitySpinnerVisible

    private val _isCalendarVisible = MutableLiveData<Boolean>()
    val isCalendarVisible: LiveData<Boolean> get() = _isCalendarVisible

    val _selectedVisit = MutableLiveData<String>()
    val selectedVisit: LiveData<String> get() = _selectedVisit

    private val _selectedDay = MutableLiveData<Int>()
    private val selectedDay: LiveData<Int> get() = _selectedDay

    private val _selectedMonth = MutableLiveData<Int>()
    private val selectedMonth: LiveData<Int> get() = _selectedMonth

    private val _selectedYear = MutableLiveData<Int>()
    private val selectedYear: LiveData<Int> get() = _selectedYear

    private var selectedCity: String? = null
    private var selectedFacility: String? = null

    val _patientName = MutableLiveData<String>()
    val patientName: LiveData<String> get() = _patientName

    val _patientLastName = MutableLiveData<String>()
    val patientLastName: LiveData<String> get() = _patientLastName

    val _patientIdNumber = MutableLiveData<String>()
    val patientIdNumber: LiveData<String> get() = _patientIdNumber

    init {
        fetchCities()
        _patientName.postValue(null)
        _patientLastName.postValue(null)
        _patientIdNumber.postValue(null)
    }

    private fun fetchCities() {
        EspressoIdlingResource.increment()
        useCaseFactory.getCitiesForSigningForVaccination
            .execute().onEach { dataState ->

                dataState.data?.let { cities ->
                    _cities.postValue(cities)
                    EspressoIdlingResource.decrement()
                }

            }.launchIn(GlobalScope)
    }

    private fun fetchFacilities(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Punkt:")
        data.add("Punkt 1")
        data.add("Punkt 2")
        data.add("Punkt 3")
        data.add("Punkt 4")
        return data
    }

    fun arePatientDataProvided(): Boolean {
       return !(patientName.value.isNullOrEmpty() || patientLastName.value.isNullOrEmpty() || patientIdNumber.value.isNullOrEmpty() || patientIdNumber.value!!.length != 11)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(PatientCalendarViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")
        if (parent!!.id == R.id.selectCitySpinner){
            selectCity(parent!!.adapter.getItem(position) as String, view)
            _selectedVisit.postValue(null)
            _isCalendarVisible.postValue(false)
        } else if (parent!!.id == R.id.selectFacilitySpinner) {
            selectFacility(parent!!.adapter.getItem(position) as String, view)
            _selectedVisit.postValue(null)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(PatientCalendarViewModel::class.java.name, "onNothingSelected:")
    }

    private fun selectCity(item: String, view: View?) {
        when (item) {

            "Miasto:" -> {
                selectedCity = null
                selectedFacility = null
                _isFacilitySpinnerVisible.postValue(false)
            }

            else -> {
                _facilities.postValue(fetchFacilities())
                selectedCity = item
                _isFacilitySpinnerVisible.postValue(true)
            }
        }
    }

    private fun selectFacility(item: String, view: View?) {
        when (item) {

            "Punkt:" -> {
                selectedFacility = null
                _isCalendarVisible.postValue(false)
            }

            else -> {
                selectedFacility = item
                _isCalendarVisible.postValue(true)
            }
        }
    }

    fun getCurrentTime() : Long {
        val calendar: Calendar = Calendar.getInstance()
        return calendar.timeInMillis
    }

    fun setMaxDate() : Long {
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, 0)
        return calendar.timeInMillis
    }

    fun selectVisits(dayOfMonth: Int) : Array<String> {
        if (dayOfMonth%2 == 0) {
            return arrayOf(
                createVisitTime(Calendar.HOUR - 2, "15"),
                createVisitTime(Calendar.HOUR - 2, "30"),
                createVisitTime(Calendar.HOUR - 2, "45"),
                createVisitTime(Calendar.HOUR - 1, "00"),
                createVisitTime(Calendar.HOUR - 1, "15"),
                createVisitTime(Calendar.HOUR - 1, "30"),
                createVisitTime(Calendar.HOUR - 1, "45"),
            )
        }
        return arrayOf()
    }

    private fun createVisitTime(hour: Int, minutes: String):String {
        return if (hour < 10) {
            "0$hour:$minutes"
        } else "$hour:$minutes"
    }

    fun getTimeAsString(context: Context): String {
        return "${context.resources.getString(R.string.patient_calendar_fragment_selected_time_text)} ${selectedVisit.value!!}"
    }

    fun getDateAsString(context: Context): String? {
            return "${context.resources.getString(R.string.patient_calendar_fragment_selected_date_text)} ${selectedDay.value}.${selectedMonth.value}.${selectedYear.value}"
    }

    fun getCityAndFacility(context: Context): String {
        return "${context.resources.getString(R.string.patient_calendar_fragment_selected_city_text)} $selectedFacility, $selectedCity"
    }

    fun registerVisit(view: View) {
        if (checkIsIdNumberIsCorrect()) {
            GlobalScope.launch(Dispatchers.Main) {
                callback.setDialog(view, view.context.getString(R.string.register_visit_dialog_text))
                delay(2000)
                callback.dismissDialog()
                callback.toastMessage(view, view.context.resources.getString(R.string.patient_calendar_fragment_registered_for_visit_text))
                Navigation.findNavController(view).navigate(R.id.action_patientCalendarFragment_to_patientFragment)
            }
        } else {
            callback.toastMessage(view, view.context.resources.getString(R.string.patient_calendar_fragment_incorrect_id_number_text))
        }
    }

    fun setCalendarView(calendar: CalendarView, childFM: FragmentManager) {
        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            _selectedVisit.postValue(null)
            var dialogFragment = VisitsDialogFragment(selectVisits(dayOfMonth))
            dialogFragment.show(childFM, "VisitsDialogFragment")
            _selectedDay.postValue(dayOfMonth)
            _selectedMonth.postValue(month + 1)
            _selectedYear.postValue(year)
        }
    }

    fun scrollToBottom(scrollView: NestedScrollView) {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun checkIsIdNumberIsCorrect() :Boolean {
        var sum = Integer.parseInt(patientIdNumber.value?.get(0).toString()) * 1 +
                Integer.parseInt(patientIdNumber.value?.get(1).toString()) * 3 +
                Integer.parseInt(patientIdNumber.value?.get(2).toString()) * 7 +
                Integer.parseInt(patientIdNumber.value?.get(3).toString()) * 9 +
                Integer.parseInt(patientIdNumber.value?.get(4).toString()) * 1 +
                Integer.parseInt(patientIdNumber.value?.get(5).toString()) * 3 +
                Integer.parseInt(patientIdNumber.value?.get(6).toString()) * 7 +
                Integer.parseInt(patientIdNumber.value?.get(7).toString()) * 9 +
                Integer.parseInt(patientIdNumber.value?.get(8).toString()) * 1 +
                Integer.parseInt(patientIdNumber.value?.get(9).toString()) * 3

        return 10 - Integer.parseInt(sum.toString().last().toString()) == Integer.parseInt(patientIdNumber.value?.get(10).toString())
    }
}