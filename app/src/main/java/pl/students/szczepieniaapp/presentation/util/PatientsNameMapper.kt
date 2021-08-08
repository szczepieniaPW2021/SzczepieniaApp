package pl.students.szczepieniaapp.presentation.util

import pl.students.szczepieniaapp.database.model.PatientEntity
import pl.students.szczepieniaapp.domain.util.DomainMapper

class PatientsNameMapper: DomainMapper<PatientEntity, String> {

    override fun mapToDomainModel(entity: PatientEntity): String {
        return "${entity.name} ${entity.lastName}"
    }

    override fun mapToDomainModelList(initial: List<PatientEntity>): List<String> {
        return initial.map { mapToDomainModel(it) }
    }
}