package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.view.View
import android.widget.AdapterView
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.presentation.MyViewModel
import javax.inject.Inject

@HiltViewModel
class PatientCalendarViewModel
@Inject
constructor(

) : MyViewModel(), AdapterView.OnItemSelectedListener {


    fun fetchCities(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Miasto:")
        data.add("Warszawa")
        data.add("Kraków")
        data.add("Poznań")
        data.add("Nowy Sącz")
        return data
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}