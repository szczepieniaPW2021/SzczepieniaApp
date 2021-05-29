package pl.students.szczepieniaapp.presentation.ui.listener

import android.graphics.Bitmap
import android.view.View

interface VaccinationQRCodeListener {

    fun setProgressDialog(view: View, qrBits: Bitmap)

    fun dismissProgressDialog()
}