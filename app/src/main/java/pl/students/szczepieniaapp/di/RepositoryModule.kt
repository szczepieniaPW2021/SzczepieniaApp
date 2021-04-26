package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.students.szczepieniaapp.network.GoogleMapRouteService
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGoogleMapRouteRepository(
        googleMapRouteService: GoogleMapRouteService,
        myRouteMapper: MyRouteMapper
    ): GoogleMapRouteRepository{
        return GoogleMapRouteRepositoryImpl(googleMapRouteService, myRouteMapper)
    }
}