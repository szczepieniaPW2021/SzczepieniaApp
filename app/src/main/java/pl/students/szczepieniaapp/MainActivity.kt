package pl.students.szczepieniaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.presentation.util.MyConnectivityManager
import javax.inject.Inject

val TAG = "c-manager"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: MyConnectivityManager

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityManager.isNetworkAvailable.observe(this, {
            Log.d(TAG, "onCreate: IS INTERNET AVAILABLE? $it")
        })

    }
}