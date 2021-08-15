package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.DriverEntity
import pl.students.szczepieniaapp.util.DataState

class GetAllDriversUseCase(
    private val database: AppDatabase,
) {

    fun execute(): Flow<DataState<List<DriverEntity>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val data = database.driverDao().getAllDrivers()
            emit(DataState.success(data))

        } catch (e: Exception) {
            emit(DataState.error<List<DriverEntity>>(e.message?: "Unknown error"))
        }
    }

}