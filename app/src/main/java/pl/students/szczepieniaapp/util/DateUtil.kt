package pl.students.szczepieniaapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    val DAY_SLASH_MONTH_SPLASH_YEAR_FORMAT = SimpleDateFormat("dd/MM/yyyy")

    fun longToDate(long: Long): Date {
        return Date(long)
    }

    fun dateToLong(date: Date): Long {
        return date.time
    }

    fun dateToString(date: Date, format: SimpleDateFormat): String{
        return format.format(date)
    }

    fun longToDateAsString(long: Long, format: SimpleDateFormat):String {
        return dateToString(longToDate(long), format)
    }

    fun stringToDate(string: String, format: SimpleDateFormat): Date {
        return format.parse(string) ?:throw NullPointerException("Could not convert date string to Date object.")
    }
}