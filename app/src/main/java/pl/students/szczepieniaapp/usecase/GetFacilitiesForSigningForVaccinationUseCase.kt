package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState

class GetFacilitiesForSigningForVaccinationUseCase {

    fun execute(abbreviation: String): Flow<DataState<ArrayList<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val cities = fetchFacilities(abbreviation = abbreviation)
            emit(DataState.success(cities))

        } catch (e: Exception) {
            emit(DataState.error<ArrayList<String>>(e.message?: "Unknown error"))
        }

    }

    private fun fetchFacilities(abbreviation: String): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Punkt:")
        data.add("Punkt ${abbreviation}1")
        data.add("Punkt ${abbreviation}2")
        data.add("Punkt ${abbreviation}3")
        data.add("Punkt ${abbreviation}4")
        return data
    }
}