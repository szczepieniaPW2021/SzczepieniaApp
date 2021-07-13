package pl.students.szczepieniaapp.presentation.ui.listener

import android.content.Context

interface VaccineOrderListener {

    fun toastMessage(context: Context, message: String)
}