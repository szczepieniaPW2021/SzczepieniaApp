package pl.students.szczepieniaapp.network.model.route

import com.google.gson.annotations.SerializedName

data class Polyline(
    @SerializedName("points")
    var points: String?
)
