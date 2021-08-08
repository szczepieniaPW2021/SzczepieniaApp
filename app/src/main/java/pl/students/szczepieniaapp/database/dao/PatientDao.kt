package pl.students.szczepieniaapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.students.szczepieniaapp.database.model.PatientEntity

@Dao
interface PatientDao {

    @Insert
    fun insertPatient(patient: PatientEntity): Long

    @Query("SELECT * FROM patients WHERE personal_number = :personalNumber")
    suspend fun getPatientByPersonalNumber(personalNumber: Long): PatientEntity?

    @Query("SELECT * FROM patients WHERE name = :name AND last_name = :lastName")
    suspend fun getPatientByName(name: String, lastName: String): PatientEntity?
}