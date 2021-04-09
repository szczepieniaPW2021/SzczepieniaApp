package pl.students.szczepieniaapp.presentation.ui.patient

import android.view.View

interface PatientListener {

    fun setProgressDialog(view: View)

    fun dismissProgressDialog()
}