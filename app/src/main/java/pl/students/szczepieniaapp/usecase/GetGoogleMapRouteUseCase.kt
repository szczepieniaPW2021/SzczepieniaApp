package pl.students.szczepieniaapp.usecase

import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository

class GetGoogleMapRouteUseCase(
    private val repository: GoogleMapRouteRepository,
    private val mapper: MyRouteMapper
) {
//    fun execute(
//        origin: String,
//        destination: String,
//        apiKey: String
//    ): Flow<DataState<MyRoute>> = flow {
//
//        try {
//            emit(DataState.loading<MyRoute>())
//
//            val routes = mapper.mapToDomainModel(
//                repository.getRoute(
//                    origin = origin,
//                    destination = destination,
//                    apiKey = apiKey
//                )[0]
//            )
//
//            emit(DataState.success(routes))
//
//        } catch (e: Exception){
//            emit(DataState.error<MyRoute>(e.message?: "Unknown error"))
//        }
//    }

    suspend fun execute(
        origin: String,
        destination: String,
        apiKey: String
    ): MyRoute? {

        try {

            return mapper.mapToDomainModel(
                repository.getRoute(
                    origin = origin,
                    destination = destination,
                    apiKey = apiKey
                )[0]
            )

        } catch (e: Exception){ }

        return null
    }
}