package pl.students.szczepieniaapp.network.model.route

import com.google.gson.annotations.SerializedName

data class StartLocation(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lng")
    var lng: Double?
)
