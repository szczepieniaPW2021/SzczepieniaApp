package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.PatientEntity
import pl.students.szczepieniaapp.util.DataState

class GetAllPatientsUseCase(
    private val database: AppDatabase
) {

    fun execute(): Flow<DataState<List<PatientEntity>?>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val data = database.patientDao().getAllPatients()
            emit(DataState.success(data))

        } catch (e: Exception) {
            emit(DataState.error<List<PatientEntity>?>(e.message?: "Unknown error"))
        }

    }
}