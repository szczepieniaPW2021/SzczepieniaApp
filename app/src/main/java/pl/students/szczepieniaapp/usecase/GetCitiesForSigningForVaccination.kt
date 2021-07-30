package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState

class GetCitiesForSigningForVaccination {

    fun execute(): Flow<DataState<ArrayList<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val cities = fetchCities()
            emit(DataState.success(cities))

        } catch (e: Exception) {
            emit(DataState.error<ArrayList<String>>(e.message?: "Unknown error"))
        }

    }

    private fun fetchCities(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Miasto:")
        data.add("Warszawa")
        data.add("Kraków")
        data.add("Poznań")
        data.add("Nowy Sącz")
        return data
    }
}