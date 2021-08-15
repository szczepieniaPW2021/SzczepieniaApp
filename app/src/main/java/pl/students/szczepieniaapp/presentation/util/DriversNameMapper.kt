package pl.students.szczepieniaapp.presentation.util

import pl.students.szczepieniaapp.database.model.DriverEntity
import pl.students.szczepieniaapp.domain.util.DomainMapper

class DriversNameMapper: DomainMapper<DriverEntity, String> {
    override fun mapToDomainModel(entity: DriverEntity): String {
        return if (entity.isAvailable) "${entity.id}. ${entity.name} ${entity.lastName}" else ""
    }

    override fun mapToDomainModelList(initial: List<DriverEntity>): List<String> {
        var list = initial.map { mapToDomainModel(it) } as MutableList<String>
        list = list.filter { x: String -> x != "" } as MutableList<String>
        return list
    }

    override fun mapFromDomainModel(domainModel: String): DriverEntity {
        val id = domainModel.substringBefore(".")
        val nameAndLastName = domainModel.substringAfter(". ")
        val name = nameAndLastName.substringBefore(" ")
        val lastName = nameAndLastName.substringAfter(" ")
       return DriverEntity(id.toInt(), name, lastName, true)
    }
}