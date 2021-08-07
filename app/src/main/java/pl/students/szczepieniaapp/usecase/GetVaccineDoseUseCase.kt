package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState
import java.util.ArrayList

class GetVaccineDoseUseCase {

    fun execute(): Flow<DataState<ArrayList<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(500L)

            val doses = fetchVaccineDose()
            emit(DataState.success(doses))

        } catch (e: Exception) {
            emit(DataState.error<ArrayList<String>>(e.message?: "Unknown error"))
        }

    }

    private fun fetchVaccineDose(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Wybierz dawkę:")
        data.add("Dawka 1")
        data.add("Dawka 2")
        return data
    }
}