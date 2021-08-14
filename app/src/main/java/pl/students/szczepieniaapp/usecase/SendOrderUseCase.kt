package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.util.ReceivedOrderMapper

class SendOrderUseCase(
    private val database: AppDatabase,
    private val mapper: ReceivedOrderMapper
) {

    fun execute(
        order: ReceivedOrder
    ): Completable {

        return Completable.create{ emitter->

            var entity = ReceivedOrder(
                order.id,
                order.orderDate,
                order.deliveryDate,
                ReceiveOrderStatus.ON_ROUTE,
                order.city,
                order.postalCode,
                order.street,
                order.orders
            )

            database.orderDao().updateOrder(mapper.mapFromDomainModel(entity))

            Thread.sleep(1000L)
            emitter.onComplete()
        }

    }
}