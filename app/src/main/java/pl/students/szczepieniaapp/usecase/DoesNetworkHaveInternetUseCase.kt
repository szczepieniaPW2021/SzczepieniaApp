package pl.students.szczepieniaapp.usecase

import java.io.IOException
import android.util.Log
import pl.students.szczepieniaapp.util.Constants.PINGING_GOOGLE_HOST
import pl.students.szczepieniaapp.util.Constants.PINGING_GOOGLE_PORT
import pl.students.szczepieniaapp.util.Constants.PINGING_GOOGLE_TIMEOUT
import java.net.InetSocketAddress
import javax.net.SocketFactory

val TAG = "c-manager"

object DoesNetworkHaveInternetUseCase {

    fun execute(socketFactory: SocketFactory): Boolean {
        return try{
            Log.d(TAG, "PINGING google.")
            val socket = socketFactory.createSocket()
            socket.connect(InetSocketAddress(PINGING_GOOGLE_HOST, PINGING_GOOGLE_PORT), PINGING_GOOGLE_TIMEOUT)
            socket.close()
            Log.d(TAG, "PING success.")
            true
        }catch (e: IOException){
            Log.e(TAG, "No internet connection. $e")
            false
        }
    }
}