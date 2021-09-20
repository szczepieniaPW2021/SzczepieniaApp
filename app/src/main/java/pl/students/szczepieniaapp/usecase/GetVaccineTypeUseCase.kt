package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState
import java.util.ArrayList

class GetVaccineTypeUseCase {

    fun execute(): Flow<DataState<ArrayList<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(500L)

            val vaccineType = fetchVaccineType()
            emit(DataState.success(vaccineType))

        } catch (e: Exception) {
            emit(DataState.error<ArrayList<String>>(e.message?: "Unknown error"))
        }

    }

    private fun fetchVaccineType(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Wybierz szczepionkę:")
        data.add("Astra Zeneca")
        data.add("Pfizer/BioNTech")
        data.add("Moderna")
        data.add("Johnson & Johnson")
        return data
    }
}