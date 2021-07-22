package pl.students.szczepieniaapp.presentation.ui.listener

import android.content.Context
import android.view.View

interface VaccineOrderListener {

    fun toastMessage(context: Context, message: String)

    fun setDialog(view: View, message: String)

    fun dismissDialog()

}