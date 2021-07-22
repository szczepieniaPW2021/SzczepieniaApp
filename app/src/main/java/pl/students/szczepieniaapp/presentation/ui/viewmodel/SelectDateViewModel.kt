package pl.students.szczepieniaapp.presentation.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SelectDateViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel() {

    fun getCurrentTime() : Long {
        val calendar: Calendar = Calendar.getInstance()
        return calendar.timeInMillis
    }

    fun getDate(year: Int, month: Int, dayOfMonth: Int) : Date {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return Date(calendar.timeInMillis)
    }

}