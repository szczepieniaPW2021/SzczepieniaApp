package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository
import pl.students.szczepieniaapp.usecase.GetCitiesForSigningForVaccination
import pl.students.szczepieniaapp.usecase.GetGoogleMapRouteUseCase
import pl.students.szczepieniaapp.usecase.GetQRCodeUseCase
import pl.students.szczepieniaapp.usecase.UseCaseFactory

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideUseCaseFactory(
        getGoogleMapRouteUseCase: GetGoogleMapRouteUseCase,
        getQRCodeUseCase: GetQRCodeUseCase,
        getCitiesForSigningForVaccination: GetCitiesForSigningForVaccination
    ): UseCaseFactory {
        return UseCaseFactory(
            getGoogleMapRouteUseCase,
            getQRCodeUseCase,
            getCitiesForSigningForVaccination
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetGoogleMapRouteUseCase(
        repository: GoogleMapRouteRepository,
        mapper: MyRouteMapper
    ): GetGoogleMapRouteUseCase {
        return GetGoogleMapRouteUseCase(repository, mapper)
    }

    @ViewModelScoped
    @Provides
    fun provideGetQRCodeUseCase(
    ): GetQRCodeUseCase {
        return GetQRCodeUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideGetCitiesForSigningForVaccination(
    ): GetCitiesForSigningForVaccination {
        return GetCitiesForSigningForVaccination()
    }

}
