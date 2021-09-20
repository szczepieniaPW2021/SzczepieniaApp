package pl.students.szczepieniaapp.usecase

import io.reactivex.Single
import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository

class GetGoogleMapRouteUseCase(
    private val repository: GoogleMapRouteRepository,
    private val mapper: MyRouteMapper
) {
   suspend fun execute(
        origin: String,
        destination: String,
        apiKey: String
    ): MyRoute? {

       var myRoutes = repository.getRoute(
           origin = origin,
           destination = destination,
           apiKey = apiKey
       )
       var routes: MyRoute? = null
       if (myRoutes != null && myRoutes.isNotEmpty()) {

           routes = mapper.mapToDomainModel(
               myRoutes[0]
           )
       }
        return routes
    }
}