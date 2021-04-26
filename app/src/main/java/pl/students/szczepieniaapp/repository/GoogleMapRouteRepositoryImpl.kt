package pl.students.szczepieniaapp.repository

import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.network.GoogleMapRouteService
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.network.model.route.Route

class GoogleMapRouteRepositoryImpl(
    private val service: GoogleMapRouteService,
    private val mapper: MyRouteMapper
) : GoogleMapRouteRepository {

    override suspend fun getRoute(
        origin: String,
        destination: String,
        apiKey: String
    ): List<MyRoute> {
        return mapper.mapToDomainModelList(service.getDirection(origin, destination, apiKey).execute().body()?.routes as List<Route>)
    }
}

