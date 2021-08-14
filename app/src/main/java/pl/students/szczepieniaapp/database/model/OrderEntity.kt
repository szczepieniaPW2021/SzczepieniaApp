package pl.students.szczepieniaapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.domain.model.Order
import java.util.ArrayList

@Entity(tableName = "orders")
data class OrderEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "order_date")
    var orderDate: Long?,

    @ColumnInfo(name = "delivery_date")
    var deliveryDate: Long?,

    @ColumnInfo(name = "city")
    var city: String?,

    @ColumnInfo(name = "street")
    var street: String?,

    @ColumnInfo(name = "postal_code")
    var postalCode: String?,

    @ColumnInfo(name = "orders")
    var orders: ArrayList<Order>,

    @ColumnInfo(name = "status")
    var status: ReceiveOrderStatus

)