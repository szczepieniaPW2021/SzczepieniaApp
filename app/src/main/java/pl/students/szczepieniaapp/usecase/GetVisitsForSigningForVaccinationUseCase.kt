package pl.students.szczepieniaapp.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.students.szczepieniaapp.util.DataState
import java.util.*

class GetVisitsForSigningForVaccinationUseCase {


    fun execute(dayOfMonth: Int): Flow<DataState<Array<String>>> = flow {

        try {
            emit(DataState.loading())
            Thread.sleep(1000L)

            val visits = selectVisits(dayOfMonth = dayOfMonth)
            emit(DataState.success(visits))

        } catch (e: Exception) {
            emit(DataState.error<Array<String>>(e.message?: "Unknown error"))
        }

    }

    private fun selectVisits(dayOfMonth: Int) : Array<String> {
        if (dayOfMonth%2 == 0) {
            return arrayOf(
                createVisitTime(Calendar.HOUR - 2, "15"),
                createVisitTime(Calendar.HOUR - 2, "30"),
                createVisitTime(Calendar.HOUR - 2, "45"),
                createVisitTime(Calendar.HOUR - 1, "00"),
                createVisitTime(Calendar.HOUR - 1, "15"),
                createVisitTime(Calendar.HOUR - 1, "30"),
                createVisitTime(Calendar.HOUR - 1, "45"),
            )
        }
        return arrayOf()
    }

    private fun createVisitTime(hour: Int, minutes: String):String {
        return if (hour < 10) {
            "0$hour:$minutes"
        } else "$hour:$minutes"
    }
}