package pl.students.szczepieniaapp.presentation.ui.listener

import android.view.View

interface DriverManagerListener {

    fun setDialog(view: View, string: String)

    fun dismissDialog()

    fun toastMessage(view: View, string: String)
}