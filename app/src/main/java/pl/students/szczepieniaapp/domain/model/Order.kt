package pl.students.szczepieniaapp.domain.model

data class Order (
    val id: Int = 0,
    val vaccineType: String? = null,
    val amount: Int = 0
)