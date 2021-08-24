package pl.students.szczepieniaapp.usecase

import android.graphics.Bitmap
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

class GetQRCodeUseCase() {

    fun execute(
        data: String?
    ): Bitmap? {

        return if (data != null){
            val qrgEncoder = QRGEncoder(data, null, QRGContents.Type.TEXT, 1000)
            qrgEncoder.bitmap
        } else null
    }
}