package pl.students.szczepieniaapp.presentation.ui.listener

import android.view.View

interface PatientCalendarListener {

    fun setDialog(view: View)

    fun dismissDialog()

    fun toastMessage(view: View, string: String)
}