package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.PatientEntity


class SignForVaccinationUseCase(
    private val database: AppDatabase
) {

    fun execute(
        id: Int?,
        name: String?,
        lastName: String?,
        personalNumber: Long?,
        vaccinationDate: Long?,
        plannedVaccinationDate: Long?,
        vaccinationCity: String?,
        vaccinationFacility: String?,
        qrCode: String?,
        vaccineDose: String?,
        vaccineType: String?
    ): Completable {

        return Completable.create { emitter->

            var patientEntity = PatientEntity(
                id,
                name,
                lastName,
                personalNumber,
                vaccinationDate,
                plannedVaccinationDate,
                vaccinationCity,
                vaccinationFacility,
                qrCode,
                vaccineDose,
                vaccineType
            )

            database.patientDao().insertPatient(patient = patientEntity)

            Thread.sleep(3000L)
            emitter.onComplete()
        }
    }
}