package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.util.ReceivedOrderMapper
import pl.students.szczepieniaapp.util.DataState

class GetReceivedOrderByIdUseCase(
    private val database: AppDatabase,
    private val mapper: ReceivedOrderMapper
) {

    fun execute(id: Int): Flow<DataState<ReceivedOrder>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(500L)

            val data = database.orderDao().getOrderById(id = id)
            emit(DataState.success(mapper.mapToDomainModel(data)))

        } catch (e: Exception) {
            emit(DataState.error<ReceivedOrder>(e.message ?: "Unknown error"))
        }
    }
}