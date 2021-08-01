package pl.students.szczepieniaapp.usecase

import io.reactivex.Completable


class SignForVaccinationUseCase {

    fun execute(): Completable {

        return Completable.create { emitter->

            Thread.sleep(3000L)
            emitter.onComplete()
        }
    }
}