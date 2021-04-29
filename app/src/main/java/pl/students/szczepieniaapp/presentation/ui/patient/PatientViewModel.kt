package pl.students.szczepieniaapp.presentation.ui.patient

import android.graphics.Bitmap
import android.view.View
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.google.zxing.WriterException
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.presentation.MyViewModel
import javax.inject.Inject

@HiltViewModel
class PatientViewModel
@Inject
constructor(

) : MyViewModel() {

    private var callback: PatientListener = PatientFragment()

    fun showQRCodePopup(view: View) {
        val qrgEncoder = QRGEncoder("123456789/ABCDEFRHIJK", null, QRGContents.Type.TEXT, 500)
        try {
            val qrBits: Bitmap = qrgEncoder.bitmap
            callback.setProgressDialog(view, qrBits)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

}