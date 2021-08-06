package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState

class GetDataForQRCodeUseCase {

    fun execute(): Flow<DataState<String>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val data = getData()
            emit(DataState.success(data))

        } catch (e: Exception) {
            emit(DataState.error<String>(e.message?: "Unknown error"))
        }

    }

    private fun getData(): String {

        return "123456789/ABCDEFRHIJK"
    }
}