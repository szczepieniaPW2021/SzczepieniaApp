package pl.students.szczepieniaapp.repository

import pl.students.szczepieniaapp.network.model.route.Route

interface GoogleMapRouteRepository {

   suspend fun getRoute(origin: String, destination: String, apiKey: String) : List<Route>
}