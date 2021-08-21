package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.util.ReceivedOrderMapper

class DeliverOderUseCase(
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
                ReceiveOrderStatus.DELIVERED,
                order.city,
                order.postalCode,
                order.street,
                order.orders,
                order.latitude,
                order.longitude,
                order.driverId
            )

            database.orderDao().updateOrder(mapper.mapFromDomainModel(entity))

            Thread.sleep(1000L)
            emitter.onComplete()
        }

    }
}