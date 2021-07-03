package pl.students.szczepieniaapp.presentation.ui.listener

import android.content.Context

interface SearchPatientListener {

    fun toastMessage(context: Context, message: String)
}