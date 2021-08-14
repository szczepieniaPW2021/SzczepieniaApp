package pl.students.szczepieniaapp.presentation.util

import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.domain.model.Order
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
            orders = entity.orders,
            deliveryStatus = entity.status,
            latitude = entity.latitude,
            longitude = entity.longitude,
            driverId = entity.driverId
        )
    }

    override fun mapToDomainModelList(initial: List<OrderEntity>): List<ReceivedOrder> {
        return initial.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainModel(domainModel: ReceivedOrder): OrderEntity {
        return OrderEntity(
            id = domainModel.id,
            orderDate = DateUtil.dateToLong(DateUtil.stringToDate(domainModel.orderDate!!, DateUtil.DAY_SLASH_MONTH_SPLASH_YEAR_FORMAT)),
            deliveryDate = DateUtil.dateToLong(DateUtil.stringToDate(domainModel.deliveryDate!!, DateUtil.DAY_SLASH_MONTH_SPLASH_YEAR_FORMAT)),
            city = domainModel.city,
            street = domainModel.street,
            postalCode = domainModel.postalCode,
            orders = domainModel.orders as ArrayList<Order>,
            status = domainModel.deliveryStatus,
            latitude = domainModel.latitude,
            longitude = domainModel.longitude,
            driverId = domainModel.driverId
        )
    }
}