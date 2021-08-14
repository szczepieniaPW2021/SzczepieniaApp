package pl.students.szczepieniaapp.database.converter

import com.google.gson.annotations.SerializedName;

enum class ReceiveOrderStatus(s: String) {

    @SerializedName("1")
    ORDERED("1"),

    @SerializedName("2")
    DELIVERED("2"),

    @SerializedName("3")
    ON_ROUTE("3")

}