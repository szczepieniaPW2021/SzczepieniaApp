package pl.students.szczepieniaapp.usecase

import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.PatientEntity

class GetPatientByIdNumberUseCase(
    private val database: AppDatabase
) {

    fun execute(idNumber: Long): PatientEntity {
        Thread.sleep(4000L)
        return database.patientDao().getPatientByPersonalNumber(idNumber)
    }
}