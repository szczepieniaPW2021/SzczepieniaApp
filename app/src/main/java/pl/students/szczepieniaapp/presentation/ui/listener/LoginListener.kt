package pl.students.szczepieniaapp.presentation.ui.listener

import android.view.View

interface LoginListener {

    fun toastMessage(view: View, message: String)
}