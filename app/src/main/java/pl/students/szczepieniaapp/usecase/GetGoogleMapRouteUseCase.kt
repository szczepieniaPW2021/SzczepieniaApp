package pl.students.szczepieniaapp.usecase

import io.reactivex.Single
import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository

class GetGoogleMapRouteUseCase(
    private val repository: GoogleMapRouteRepository,
    private val mapper: MyRouteMapper
) {
    fun execute(
        origin: String,
        destination: String,
        apiKey: String
    ): Single<MyRoute> {

            val routes = mapper.mapToDomainModel(
                repository.getRoute(
                    origin = origin,
                    destination = destination,
                    apiKey = apiKey
                )[0]
            )

        return Single.just(routes)
    }
}