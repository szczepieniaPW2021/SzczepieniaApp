package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.students.szczepieniaapp.network.GoogleMapRouteService
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMyRouteMapper() : MyRouteMapper {
        return MyRouteMapper()
    }

    @Singleton
    @Provides
    fun provideGoogleMapRouteService() : GoogleMapRouteService {
        return Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_APIS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleMapRouteService::class.java)
    }
}