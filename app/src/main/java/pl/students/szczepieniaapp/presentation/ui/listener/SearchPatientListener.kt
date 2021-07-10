package pl.students.szczepieniaapp.presentation.ui.listener

import android.content.Context
import android.view.View

interface SearchPatientListener {

    fun toastMessage(context: Context, message: String)

    fun setDialog(view: View, string: String)

    fun dismissDialog()
}