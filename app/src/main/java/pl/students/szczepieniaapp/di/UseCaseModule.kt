package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.students.szczepieniaapp.network.mapper.MyRouteMapper
import pl.students.szczepieniaapp.repository.GoogleMapRouteRepository
import pl.students.szczepieniaapp.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideUseCaseFactory(
        getGoogleMapRouteUseCase: GetGoogleMapRouteUseCase,
        getQRCodeUseCase: GetQRCodeUseCase,
        getCitiesForSigningForVaccinationUseCase: GetCitiesForSigningForVaccinationUseCase,
        getFacilitiesForSigningForVaccinationUseCase: GetFacilitiesForSigningForVaccinationUseCase,
        getVisitsForSigningForVaccinationUseCase: GetVisitsForSigningForVaccinationUseCase,
        signForVaccinationUseCase: SignForVaccinationUseCase
    ): UseCaseFactory {
        return UseCaseFactory(
            getGoogleMapRouteUseCase,
            getQRCodeUseCase,
            getCitiesForSigningForVaccinationUseCase,
            getFacilitiesForSigningForVaccinationUseCase,
            getVisitsForSigningForVaccinationUseCase,
            signForVaccinationUseCase
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
    ): GetCitiesForSigningForVaccinationUseCase {
        return GetCitiesForSigningForVaccinationUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideGetFacilitiesForSigningForVaccination(
    ): GetFacilitiesForSigningForVaccinationUseCase {
        return GetFacilitiesForSigningForVaccinationUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideGetVisitsForSigningForVaccination(
    ): GetVisitsForSigningForVaccinationUseCase {
        return GetVisitsForSigningForVaccinationUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideSignForVaccinationUseCase(
    ): SignForVaccinationUseCase {
        return SignForVaccinationUseCase()
    }

}
