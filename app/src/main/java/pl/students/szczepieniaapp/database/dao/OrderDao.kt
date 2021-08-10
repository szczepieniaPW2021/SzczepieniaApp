package pl.students.szczepieniaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import pl.students.szczepieniaapp.database.model.OrderEntity

@Dao
interface OrderDao {

    @Insert
    fun insertOrder(order: OrderEntity): Long
}