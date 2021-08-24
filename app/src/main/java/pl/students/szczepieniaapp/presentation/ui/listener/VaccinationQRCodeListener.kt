package pl.students.szczepieniaapp.presentation.ui.listener

import android.graphics.Bitmap
import android.view.View

interface VaccinationQRCodeListener {

    fun setQRCodeDialog(view: View, qrBits: Bitmap)

    fun setNoQRCodeDialog(view: View)

}