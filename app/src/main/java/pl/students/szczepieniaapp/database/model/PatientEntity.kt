package pl.students.szczepieniaapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class PatientEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "last_name")
    var lastName: String?,

    @ColumnInfo(name = "personal_number")
    var personalNumber: Long?,

    @ColumnInfo(name = "vaccination_date")
    var vaccinationDate: Long?,

    @ColumnInfo(name = "planned_date")
    var plannedVaccinationDate: Long?,

    @ColumnInfo(name = "vaccination_city")
    var vaccinationCity: String?,

    @ColumnInfo(name = "vaccination_facility")
    var vaccinationFacility: String?,

    @ColumnInfo(name = "qr_code")
    var qrCode: String?,

    @ColumnInfo(name = "vaccine_dose")
    var vaccineDose: String?,

    @ColumnInfo(name = "vaccine_type")
    var vaccineType: String?,
)