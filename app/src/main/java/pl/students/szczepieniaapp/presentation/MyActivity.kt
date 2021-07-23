package pl.students.szczepieniaapp.presentation

import androidx.appcompat.app.AppCompatActivity
import pl.students.szczepieniaapp.presentation.util.MyConnectivityManager
import javax.inject.Inject

abstract class MyActivity<Binding> : AppCompatActivity() {

    protected var _binding: Binding? = null
    protected val binding get() = _binding!!

    @Inject
    lateinit var connectivityManager: MyConnectivityManager

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
        _binding = null
    }

    companion object{
        fun buildToastMessage(string: String): String{
            return "$string"
        }
    }
}