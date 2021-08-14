package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable
import pl.students.szczepieniaapp.database.AppDatabase
import pl.students.szczepieniaapp.database.model.DriverEntity

class AddDriverUseCase(
    private val database: AppDatabase
) {

    fun execute(
        name: String,
        lastName: String
    ): Completable {

        return Completable.create { emitter->

            var driver = DriverEntity(
                null, name, lastName
            )

            database.driverDao().insertDriver(driver = driver)

            Thread.sleep(2000L)
            emitter.onComplete()
        }
    }
}