package pl.students.szczepieniaapp.usecase

class UseCaseFactory(
    val getGoogleMapRouteUseCase: GetGoogleMapRouteUseCase,
    val getQRCodeUseCase: GetQRCodeUseCase,
    val getCitiesForSigningForVaccinationUseCase: GetCitiesForSigningForVaccinationUseCase,
    val getFacilitiesForSigningForVaccinationUseCase: GetFacilitiesForSigningForVaccinationUseCase
)

