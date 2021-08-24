package pl.students.szczepieniaapp.usecase

import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.util.ReceivedOrderMapper

class GetOrderByDriverIdUseCase(
    private val database: AppDatabase,
    private val mapper: ReceivedOrderMapper
) {

    suspend fun execute(id: Int): ReceivedOrder {

        Thread.sleep(1000L)
        return mapper.mapToDomainModel(
            database.orderDao().getOrderByDriverId(id)
        )
    }
}