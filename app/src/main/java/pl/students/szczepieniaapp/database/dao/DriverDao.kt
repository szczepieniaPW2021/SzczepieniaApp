package pl.students.szczepieniaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import pl.students.szczepieniaapp.database.model.DriverEntity

@Dao
interface DriverDao {

    @Insert
    fun insertDriver(driver: DriverEntity): Long
}