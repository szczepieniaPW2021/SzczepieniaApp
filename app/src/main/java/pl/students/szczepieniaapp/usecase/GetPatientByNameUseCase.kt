package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState
import java.util.HashMap

class GetPatientByNameUseCase {

    fun execute(name: String): Flow<DataState<List<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(4000L)

            val patient = getPatientByName(name = name)
            emit(DataState.success(patient))

        } catch (e: Exception) {
            emit(DataState.error<List<String>>(e.message ?: "Unknown error"))
        }

    }

    private fun getPatientByName(name: String): List<String> {
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

        patients.filterValues { it == name }.keys.let {
            if (it.isNotEmpty()) {
                person.add(name)
                person.add(it.first().toString())
            }
        }

        return person
    }
}