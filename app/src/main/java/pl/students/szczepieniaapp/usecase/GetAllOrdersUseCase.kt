package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.util.ReceivedOrderMapper
import pl.students.szczepieniaapp.util.DataState

class GetAllOrdersUseCase(
    private val database: AppDatabase,
    private val mapper: ReceivedOrderMapper
) {

    fun execute(): Flow<DataState<List<ReceivedOrder>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val data = database.orderDao().getAllOrders()
            emit(DataState.success(mapper.mapToDomainModelList(data)))

        } catch (e: Exception) {
            emit(DataState.error<List<ReceivedOrder>>(e.message?: "Unknown error"))
        }

    }
}