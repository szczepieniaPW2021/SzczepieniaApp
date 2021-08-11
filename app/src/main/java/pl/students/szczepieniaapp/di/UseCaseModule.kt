package pl.students.szczepieniaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.students.szczepieniaapp.database.AppDatabase
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
        signForVaccinationUseCase: SignForVaccinationUseCase,
        getDataForQRCodeUseCase: GetDataForQRCodeUseCase,
        getPatientByNameUseCase: GetPatientByNameUseCase,
        getPatientByIdNumberUseCase: GetPatientByIdNumberUseCase,
        getVaccineDoseUseCase: GetVaccineDoseUseCase,
        getVaccineTypeUseCase: GetVaccineTypeUseCase,
        registerVaccinationUseCase: RegisterVaccinationUseCase,
        orderVaccineUseCase: OrderVaccineUseCase,
        getDestinationCoordinatesUseCase: GetDestinationCoordinatesUseCase,
        getAllPatientsUseCase: GetAllPatientsUseCase,
        getAllOrdersUseCase: GetAllOrdersUseCase
    ): UseCaseFactory {
        return UseCaseFactory(
            getGoogleMapRouteUseCase,
            getQRCodeUseCase,
            getCitiesForSigningForVaccinationUseCase,
            getFacilitiesForSigningForVaccinationUseCase,
            getVisitsForSigningForVaccinationUseCase,
            signForVaccinationUseCase,
            getDataForQRCodeUseCase,
            getPatientByNameUseCase,
            getPatientByIdNumberUseCase,
            getVaccineDoseUseCase,
            getVaccineTypeUseCase,
            registerVaccinationUseCase,
            orderVaccineUseCase,
            getDestinationCoordinatesUseCase,
            getAllPatientsUseCase,
            getAllOrdersUseCase
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
        database: AppDatabase
    ): SignForVaccinationUseCase {
        return SignForVaccinationUseCase(database)
    }

    @ViewModelScoped
    @Provides
    fun provideGetDataForQRCodeUseCase(
    ): GetDataForQRCodeUseCase {
        return GetDataForQRCodeUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideGetPatientByNameUseCase(
        database: AppDatabase
    ): GetPatientByNameUseCase {
        return GetPatientByNameUseCase(database)
    }

    @ViewModelScoped
    @Provides
    fun provideGetPatientByIdNumberUseCase(
        database: AppDatabase
    ): GetPatientByIdNumberUseCase {
        return GetPatientByIdNumberUseCase(database)
    }

    @ViewModelScoped
    @Provides
    fun provideGetVaccineDoseUseCase(
    ): GetVaccineDoseUseCase {
        return GetVaccineDoseUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideGetVaccineTypeUseCase(
    ): GetVaccineTypeUseCase {
        return GetVaccineTypeUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideRegisterVaccinationUseCase(
        database: AppDatabase
    ): RegisterVaccinationUseCase {
        return RegisterVaccinationUseCase(database)
    }

    @ViewModelScoped
    @Provides
    fun provideOrderVaccineUseCase(
        database: AppDatabase
    ): OrderVaccineUseCase {
        return OrderVaccineUseCase(database)
    }

    @ViewModelScoped
    @Provides
    fun provideGetDestinationCoordinatesUseCase(
    ): GetDestinationCoordinatesUseCase {
        return GetDestinationCoordinatesUseCase()
    }

    @ViewModelScoped
    @Provides
    fun provideGetAllPatientsUseCase(
        database: AppDatabase
    ): GetAllPatientsUseCase {
        return GetAllPatientsUseCase(database)
    }

    @ViewModelScoped
    @Provides
    fun provideGetAllOrdersUseCase(
        database: AppDatabase
    ): GetAllOrdersUseCase {
        return GetAllOrdersUseCase(database)
    }
}
