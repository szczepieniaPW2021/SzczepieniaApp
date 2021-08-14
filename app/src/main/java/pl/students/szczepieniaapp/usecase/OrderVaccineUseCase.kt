package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.domain.model.Order
import java.util.ArrayList

class OrderVaccineUseCase(
    private val database: AppDatabase
) {

    fun execute(
        id: Int?,
        orderDate: Long?,
        deliveryDate: Long?,
        city: String?,
        street: String?,
        postalCode: String?,
        orderList: ArrayList<Order>
    ): Completable {

        return Completable.create { emitter->

            var orderEntity = OrderEntity(
                id,
                orderDate,
                deliveryDate,
                city,
                street,
                postalCode,
                orderList,
                ReceiveOrderStatus.ORDERED
            )

            database.orderDao().insertOrder(order = orderEntity)

            Thread.sleep(2000L)
            emitter.onComplete()
        }
    }
}