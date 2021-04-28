package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.students.szczepieniaapp.network.GoogleMapRouteService
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGoogleMapRouteRepository(
        googleMapRouteService: GoogleMapRouteService,
    ): GoogleMapRouteRepository{
        return GoogleMapRouteRepositoryImpl(googleMapRouteService)
    }
}