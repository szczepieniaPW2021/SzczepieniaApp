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
    val getVaccineTypeUseCase: GetVaccineTypeUseCase,
    val registerVaccinationUseCase: RegisterVaccinationUseCase,
    val orderVaccineUseCase: OrderVaccineUseCase,
    val getDestinationCoordinatesUseCase: GetDestinationCoordinatesUseCase,
    val getAllPatientsUseCase: GetAllPatientsUseCase,
    val getAllOrdersUseCase: GetAllOrdersUseCase,
    val getReceivedOrderByIdUseCase: GetReceivedOrderByIdUseCase
)

