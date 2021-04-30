package pl.students.szczepieniaapp.usecase

import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.network.GoogleMapRouteService
import pl.students.szczepieniaapp.network.data.MockWebServiceResponses.routeWarsawWroclaw
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class GetGoogleMapRouteUseCaseTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private lateinit var googleMapRouteService: GoogleMapRouteService
    private val origin = "10.1254,-51.2365" // can be anything
    private val destination = "This doesn't matter" // can be anything
    private val key = "Some random text" // can be anything

    //tested
    private lateinit var getGoogleMapRouteUseCase: GetGoogleMapRouteUseCase

    //dependencies
    private lateinit var repository: GoogleMapRouteRepository
    private val mapper = MyRouteMapper()

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/maps/api/directions/json/")
        googleMapRouteService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(GoogleMapRouteService::class.java)

        //instantiate the system in test
        repository = GoogleMapRouteRepositoryImpl(googleMapRouteService)
        getGoogleMapRouteUseCase = GetGoogleMapRouteUseCase(
            repository = repository,
            mapper = mapper
        )

    }

    /**
     * Simulate a good request
     */
    @Test
    fun getRouteFromNetwork_emitToUI(): Unit = runBlocking {

        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(routeWarsawWroclaw)
        )

        val flowItem = getGoogleMapRouteUseCase.execute(origin, destination, key).toList()

        // first emission should be `loading`
        assert(flowItem[0].loading)

        // second emission should be `data`
        val route = flowItem[1].data

        // confirm they it is actually MyRoute object
        assert(route is MyRoute)

        // loading should be false now
        assert(!flowItem[1].loading)
    }

    /**
     * Simulate a bad request
     */
    @Test
    fun getRouteFromNetwork_emitHttpError(): Unit = runBlocking {

        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .setBody("{}")
        )

        val flowItem = getGoogleMapRouteUseCase.execute(origin, destination, key).toList()

        // first emission should be `loading`
        assert(flowItem[0].loading)

        // second emission should be the exception
        val error = flowItem[1].error
        assert(error != null)

        // loading should be false now
        assert(!flowItem[1].loading)
    }

    @AfterEach
    fun reset() {
        mockWebServer.shutdown()
    }
}