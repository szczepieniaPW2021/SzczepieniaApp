package pl.students.szczepieniaapp.presentation.util

import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.domain.util.DomainMapper
import pl.students.szczepieniaapp.util.DateUtil

class ReceivedOrderMapper: DomainMapper<OrderEntity, ReceivedOrder> {

    override fun mapToDomainModel(entity: OrderEntity): ReceivedOrder {
        return ReceivedOrder(
            id = entity.id!!,
            orderDate = DateUtil.longToDateAsString(entity.orderDate!!, DateUtil.DAY_SLASH_MONTH_SPLASH_YEAR_FORMAT),
            deliveryDate = DateUtil.longToDateAsString(entity.deliveryDate!!, DateUtil.DAY_SLASH_MONTH_SPLASH_YEAR_FORMAT),
            city = entity.city,
            street = entity.street,
            postalCode = entity.postalCode,
            orders = entity.orders
        )
    }

    override fun mapToDomainModelList(initial: List<OrderEntity>): List<ReceivedOrder> {
        return initial.map { mapToDomainModel(it) }
    }
}