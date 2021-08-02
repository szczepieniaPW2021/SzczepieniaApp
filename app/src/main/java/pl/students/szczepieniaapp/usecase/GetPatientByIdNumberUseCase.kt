package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState
import java.util.HashMap

class GetPatientByIdNumberUseCase {

    fun execute(idNumber: Long): Flow<DataState<List<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(4000L)

            val patient = getPatientByIdNumber(idNumber = idNumber)
            emit(DataState.success(patient))

        } catch (e: Exception) {
            emit(DataState.error<List<String>>(e.message ?: "Unknown error"))
        }

    }

    private fun getPatientByIdNumber(idNumber: Long): List<String> {
        val patients: HashMap<Long, String> = hashMapOf()
        patients[12345678900L] = "Jan Nowak"
        patients[12345678901L] = "Tomasz Kowalski"
        patients[12345678902L] = "Paweł Podgórski"
        patients[12345678903L] = "Jacek Nowak"
        patients[12345678904L] = "Robert Nowacki"
        patients[12345678905L] = "Mateusz Kowalski"
        patients[12345678906L] = "Szymon Jackowiak"
        patients[12345678907L] = "Tomasz Zięba"

        var person: MutableList<String> = mutableListOf()

        patients[idNumber]?.let {
            person.add(it)
            person.add(idNumber.toString())
        }

        return person
    }
}

//    fun execute(abbreviation: String): Flow<DataState<ArrayList<String>>> = flow {
//
//        try {
//            emit(DataState.loading())
//            Thread.sleep(1000L)
//
//            val cities = fetchFacilities(abbreviation = abbreviation)
//            emit(DataState.success(cities))
//
//        } catch (e: Exception) {
//            emit(DataState.error<ArrayList<String>>(e.message?: "Unknown error"))
//        }
//
//    }
//
//    private fun fetchFacilities(abbreviation: String): ArrayList<String> {
//        val data: ArrayList<String> = arrayListOf()
//        data.add("Punkt:")
//        data.add("Punkt ${abbreviation}1")
//        data.add("Punkt ${abbreviation}2")
//        data.add("Punkt ${abbreviation}3")
//        data.add("Punkt ${abbreviation}4")
//        return data
//    }