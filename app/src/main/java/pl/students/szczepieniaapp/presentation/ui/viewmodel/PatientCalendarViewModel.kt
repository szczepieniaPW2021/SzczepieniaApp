package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.CalendarView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.PatientCalendarFragment
import pl.students.szczepieniaapp.presentation.ui.listener.PatientCalendarListener
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class PatientCalendarViewModel
@Inject
constructor(

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

    private val _visits = MutableLiveData<Array<String>>()
    val visits: LiveData<Array<String>> get() = _visits

    private var selectedCity: String? = null
    private var selectedFacility: String? = null


    init {
        _cities.postValue(fetchCities())
    }

    private fun fetchCities(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Miasto:")
        data.add("Warszawa")
        data.add("Kraków")
        data.add("Poznań")
        data.add("Nowy Sącz")
        return data
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(PatientCalendarViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")
        if (parent!!.id == R.id.selectCitySpinner){
            selectCity(parent!!.adapter.getItem(position) as String, view)
        } else if (parent!!.id == R.id.selectFacilitySpinner) {
            selectFacility(parent!!.adapter.getItem(position) as String, view)
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
                _isCalendarVisible.postValue(false)
            }

            else -> {
                _facilities.postValue(fetchFacilities())
                selectedCity = item
                _isFacilitySpinnerVisible.postValue(true)
                _isCalendarVisible.postValue(false)
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
                selectedCity = item
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

    fun getDate(calendarView: CalendarView) {
        calendarView.setOnDateChangeListener {view, year, month, dayOfMonth ->
            Log.d("testuje", "getDate: $year, $month, $dayOfMonth")
            _visits.postValue(selectVisits(dayOfMonth))
        }
    }

    private fun selectVisits(dayOfMonth: Int) : Array<String> {
        val calendar: Calendar = Calendar.getInstance()
        val data: Array<String> = arrayOf()
        if (dayOfMonth%2 == 0) {
            calendar.set(Calendar.YEAR, Calendar.MONTH, dayOfMonth, Calendar.HOUR - 2, 15)
            data[0] = Date(calendar.timeInMillis).toString()
            calendar.set(Calendar.YEAR, Calendar.MONTH, dayOfMonth, Calendar.HOUR - 2, 30)
            data[1] = Date(calendar.timeInMillis).toString()
            calendar.set(Calendar.YEAR, Calendar.MONTH, dayOfMonth, Calendar.HOUR - 1, 0)
            data[2] = Date(calendar.timeInMillis).toString()
        }
        return data
    }
}