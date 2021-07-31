package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState

class GetFacilitiesForSigningForVaccinationUseCase {

    fun execute(letter: Char): Flow<DataState<ArrayList<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val cities = fetchFacilities(letter = letter)
            emit(DataState.success(cities))

        } catch (e: Exception) {
            emit(DataState.error<ArrayList<String>>(e.message?: "Unknown error"))
        }

    }

    private fun fetchFacilities(letter: Char): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Punkt:")
        data.add("Punkt ${letter}1")
        data.add("Punkt ${letter}2")
        data.add("Punkt ${letter}3")
        data.add("Punkt ${letter}4")
        return data
    }
}