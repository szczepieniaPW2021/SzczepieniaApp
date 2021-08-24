package pl.students.szczepieniaapp.domain.util

interface DomainMapper <T, DomainModel> {

    fun mapToDomainModel(entity: T) : DomainModel

    fun mapToDomainModelList(initial: List<T>): List<DomainModel>

    fun mapFromDomainModel(domainModel: DomainModel) : T

}