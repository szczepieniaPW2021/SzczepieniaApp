package pl.students.szczepieniaapp.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.students.szczepieniaapp.domain.model.Order
import java.util.ArrayList

class Converters {

    @TypeConverter
    fun fromString(value: String?): ArrayList<Order> {
        val listType = object : TypeToken<ArrayList<Order>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Order?>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromDoubleToString(double: Double):String {
        return double.toString()
    }

    @TypeConverter
    fun fromStringToDouble(string: String):Double {
        return string.toDouble()
    }
}