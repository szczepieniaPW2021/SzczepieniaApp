package pl.students.szczepieniaapp.presentation

import android.view.View

interface MyListener {

    fun toastMessage(view: View, message: String)

    fun setDialog(view: View, string: String)

    fun dismissDialog()
}