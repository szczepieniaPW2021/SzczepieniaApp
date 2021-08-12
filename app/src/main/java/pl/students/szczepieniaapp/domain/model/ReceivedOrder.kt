package pl.students.szczepieniaapp.domain.model

data class ReceivedOrder(
    val id: Int = 0,
    val orderDate: String? = null,
    val deliveryDate: String? = null,
    val deliveryStatus: Boolean = false,
    val city: String? = null,
    val postalCode: String? = null,
    val street: String? = null
)