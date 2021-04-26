package pl.students.szczepieniaapp.repository

import pl.students.szczepieniaapp.domain.model.MyRoute

interface GoogleMapRouteRepository {

    suspend fun getRoute(origin: String, destination: String, apiKey: String) :  List<MyRoute>
}