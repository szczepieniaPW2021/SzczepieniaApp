package pl.students.szczepieniaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pl.students.szczepieniaapp.database.model.DriverEntity
import pl.students.szczepieniaapp.database.model.OrderEntity

@Dao
interface DriverDao {

    @Insert
    fun insertDriver(driver: DriverEntity): Long

    @Query("SELECT * FROM drivers WHERE is_available = 1")
    suspend fun getAllAvailableDrivers(): List<DriverEntity>

    @Query("SELECT * FROM drivers")
    suspend fun getAllDrivers(): List<DriverEntity>

    @Update
    fun updateDriver(driver: DriverEntity)
}