package pl.students.szczepieniaapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.students.szczepieniaapp.database.converter.Converters
import pl.students.szczepieniaapp.database.dao.DriverDao
import pl.students.szczepieniaapp.database.dao.OrderDao
import pl.students.szczepieniaapp.database.dao.PatientDao
import pl.students.szczepieniaapp.database.model.DriverEntity
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.database.model.PatientEntity

@Database(entities = [PatientEntity::class, OrderEntity::class, DriverEntity::class ], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun patientDao(): PatientDao

    abstract fun orderDao(): OrderDao

    abstract fun driverDao(): DriverDao

    companion object{
        val DATABASE_NAME: String = "szczepienia_db"
    }
}