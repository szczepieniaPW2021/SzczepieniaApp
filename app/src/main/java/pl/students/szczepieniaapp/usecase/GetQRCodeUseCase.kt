package pl.students.szczepieniaapp.usecase

import android.graphics.Bitmap
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState

class GetQRCodeUseCase() {

    fun execute(
        data: String
    ): Flow<DataState<Bitmap>> = flow {

        try {
            emit(DataState.loading<Bitmap>())

            val qrgEncoder = QRGEncoder(data, null, QRGContents.Type.TEXT, 500)
            val qrBits: Bitmap = qrgEncoder.bitmap

            emit(DataState.success(qrBits))

        } catch (e: Exception){
            emit(DataState.error<Bitmap>(e.message?: "Unknown error"))
        }
    }
}