package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository
import pl.students.szczepieniaapp.usecase.GetGoogleMapRouteUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetGoogleMapRouteUseCase(
        repository: GoogleMapRouteRepository,
        mapper: MyRouteMapper
    ): GetGoogleMapRouteUseCase {
        return GetGoogleMapRouteUseCase(repository, mapper)
    }

}
