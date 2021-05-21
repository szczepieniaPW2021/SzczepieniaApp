package pl.students.szczepieniaapp.presentation.ui.login

import android.view.View

interface LoginListener {

    fun toastMessage(view: View, message: String)
}