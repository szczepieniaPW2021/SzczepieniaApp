package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.PatientEntity

class RegisterVaccinationUseCase(
    private val database: AppDatabase
) {

    fun execute(
        patient: PatientEntity,
        vaccineDose: String,
        vaccineType: String,
        qrCode: String?,
        vaccinationDate: Long
    ): Completable {

        return Completable.create { emitter->

            var patientEntity = PatientEntity(
                patient.id,
                patient.name,
                patient.lastName,
                patient.personalNumber,
                vaccinationDate,
                patient.plannedVaccinationDate,
                patient.vaccinationCity,
                patient.vaccinationFacility,
                qrCode,
                vaccineDose,
                vaccineType
            )

            database.patientDao().updatePatient(patient = patientEntity)

            Thread.sleep(1000L)
            emitter.onComplete()
        }
    }
}