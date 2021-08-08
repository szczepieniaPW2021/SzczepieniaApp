package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.PatientEntity
import pl.students.szczepieniaapp.util.DataState

class GetPatientByNameUseCase(
    private val database: AppDatabase
) {

    fun execute(name: String, lastName: String): Flow<DataState<PatientEntity?>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(4000L)

            val patient = database.patientDao().getPatientByName(name = name, lastName = lastName)
            emit(DataState.success(patient))

        } catch (e: Exception) {
            emit(DataState.error<PatientEntity?>(e.message ?: "Unknown error"))
        }

    }
}