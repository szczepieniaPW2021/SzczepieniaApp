package pl.students.szczepieniaapp.presentation.ui.patient

import android.graphics.Bitmap
import android.view.View

interface PatientListener {

    fun setProgressDialog(view: View, qrBits: Bitmap)

    fun dismissProgressDialog()
}