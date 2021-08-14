package pl.students.szczepieniaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pl.students.szczepieniaapp.database.model.OrderEntity

@Dao
interface OrderDao {

    @Insert
    fun insertOrder(order: OrderEntity): Long

    @Update
    fun updateOrder(order: OrderEntity)

    @Query("SELECT * FROM orders ORDER BY order_date ASC")
    suspend fun getAllOrders(): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrderById(id: Int): OrderEntity
}