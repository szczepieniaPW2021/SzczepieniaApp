package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.presentation.util.DriversNameMapper

class MakeDriverUnavailableUseCase(
    private val database: AppDatabase,
    private val mapper: DriversNameMapper
) {

    fun execute(
        driver: String
    ): Completable {

        return Completable.create { emitter ->

            var updatedDriver = mapper.mapFromDomainModel(driver)
            updatedDriver.isAvailable = false

            database.driverDao().updateDriver(updatedDriver)

            Thread.sleep(1000L)
            emitter.onComplete()
        }
    }
}