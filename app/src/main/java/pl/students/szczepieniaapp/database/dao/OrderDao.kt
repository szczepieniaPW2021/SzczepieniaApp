package pl.students.szczepieniaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.database.model.PatientEntity

@Dao
interface OrderDao {

    @Insert
    fun insertOrder(order: OrderEntity): Long

    @Query("SELECT * FROM orders ORDER BY order_date ASC")
    suspend fun getAllOrders(): List<OrderEntity>
}