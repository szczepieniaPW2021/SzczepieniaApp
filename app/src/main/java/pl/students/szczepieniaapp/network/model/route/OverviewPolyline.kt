package pl.students.szczepieniaapp.network.model.route

import com.google.gson.annotations.SerializedName

data class OverviewPolyline(
    @SerializedName("points")
    var points: String?
)
