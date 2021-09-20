package pl.students.szczepieniaapp.usecase

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import pl.students.szczepieniaapp.BuildConfig

class GetDestinationCoordinatesUseCase {

    fun execute(): Single<LatLng> {

        Thread.sleep(2000L)

        return Single.just(LatLng(BuildConfig.Lat, BuildConfig.Lng))
    }
}