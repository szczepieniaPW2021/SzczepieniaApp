package pl.students.szczepieniaapp.usecase

class UseCaseFactory(
    val getGoogleMapRouteUseCase: GetGoogleMapRouteUseCase,
    val getQRCodeUseCase: GetQRCodeUseCase,
    val getCitiesForSigningForVaccinationUseCase: GetCitiesForSigningForVaccinationUseCase,
    val getFacilitiesForSigningForVaccinationUseCase: GetFacilitiesForSigningForVaccinationUseCase,
    val getVisitsForSigningForVaccinationUseCase: GetVisitsForSigningForVaccinationUseCase,
    val signForVaccinationUseCase: SignForVaccinationUseCase,
    val getDataForQRCodeUseCase: GetDataForQRCodeUseCase,
    val getPatientByNameUseCase: GetPatientByNameUseCase,
    val getPatientByIdNumberUseCase: GetPatientByIdNumberUseCase,
    val getVaccineDoseUseCase: GetVaccineDoseUseCase,
    val getVaccineTypeUseCase: GetVaccineTypeUseCase
)

