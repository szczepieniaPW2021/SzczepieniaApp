package pl.students.szczepieniaapp.repository

import pl.students.szczepieniaapp.network.GoogleMapRouteService
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.network.model.route.Route

class GoogleMapRouteRepositoryImpl(
    private val service: GoogleMapRouteService,
) : GoogleMapRouteRepository {

    override suspend fun getRoute(
        origin: String,
        destination: String,
        apiKey: String
    ): List<Route> {
        return service.getDirection(origin, destination, apiKey).execute().body()?.routes as List<Route>
    }
}

