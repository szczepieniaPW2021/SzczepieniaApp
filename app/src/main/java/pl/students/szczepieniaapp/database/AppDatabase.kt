package pl.students.szczepieniaapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.students.szczepieniaapp.database.dao.PatientDao
import pl.students.szczepieniaapp.database.model.PatientEntity

@Database(entities = [PatientEntity::class ], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun patientDao(): PatientDao

    companion object{
        val DATABASE_NAME: String = "szczepienia_db"
    }
}