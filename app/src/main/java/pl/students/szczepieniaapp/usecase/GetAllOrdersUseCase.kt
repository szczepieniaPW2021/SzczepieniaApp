package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.util.DataState

class GetAllOrdersUseCase(
    private val database: AppDatabase
) {

    fun execute(): Flow<DataState<List<OrderEntity>?>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val data = database.orderDao().getAllOrders()
            emit(DataState.success(data))

        } catch (e: Exception) {
            emit(DataState.error<List<OrderEntity>?>(e.message?: "Unknown error"))
        }

    }
}