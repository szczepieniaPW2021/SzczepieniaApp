package pl.students.szczepieniaapp.network

import pl.students.szczepieniaapp.network.model.route.DirectionResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMapRouteService {

    @GET("maps/api/directions/json")
    fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): Call<DirectionResponses>
}