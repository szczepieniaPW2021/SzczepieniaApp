package pl.students.szczepieniaapp.network.model.route

import com.google.gson.annotations.SerializedName

data class Distance(
    @SerializedName("text")
    var text: String?,
    @SerializedName("value")
    var value: Int?
)
