package pl.students.szczepieniaapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.ActivityDoctorBinding
import pl.students.szczepieniaapp.databinding.ActivityFacilityManagerBinding
import pl.students.szczepieniaapp.presentation.util.MyConnectivityManager
import javax.inject.Inject

@AndroidEntryPoint
class FacilityManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacilityManagerBinding

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
        binding = ActivityFacilityManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectivityManager.isNetworkAvailable.observe(this, {
            if (it) binding.noConnectionBanner.visibility = View.GONE else binding.noConnectionBanner.visibility = View.VISIBLE
        })
    }

    companion object{
        fun buildToastMessage(string: String): String{
            return "$string"
        }
    }
}