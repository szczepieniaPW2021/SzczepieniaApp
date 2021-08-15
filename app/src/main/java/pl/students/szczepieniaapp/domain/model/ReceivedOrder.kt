package pl.students.szczepieniaapp.domain.model

import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus


data class ReceivedOrder(
    val id: Int = 0,
    val orderDate: String? = null,
    val deliveryDate: String? = null,
    val deliveryStatus: ReceiveOrderStatus = ReceiveOrderStatus.ORDERED,
    val city: String? = null,
    val postalCode: String? = null,
    val street: String? = null,
    val orders: List<Order>? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    var driverId: Int? = null
)