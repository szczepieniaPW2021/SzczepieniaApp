package pl.students.szczepieniaapp.usecase

import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.PatientEntity

class GetPatientByNameUseCase(
    private val database: AppDatabase
) {
    fun execute(name: String, lastName: String): PatientEntity {
        Thread.sleep(4000L)
        return database.patientDao().getPatientByName(name = name, lastName = lastName)
    }
}