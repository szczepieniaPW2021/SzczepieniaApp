package pl.students.szczepieniaapp.presentation.ui.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.ActivityDoctorBinding
import pl.students.szczepieniaapp.presentation.MyActivity

@AndroidEntryPoint
class DoctorActivity : MyActivity<ActivityDoctorBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.doctorBottomNav.setupWithNavController(Navigation.findNavController(this, R.id.activityDoctorFragmentContainerView))

        connectivityManager.isNetworkAvailable.observe(this, {
            if (it) binding.noConnectionBanner.visibility = View.GONE else binding.noConnectionBanner.visibility = View.VISIBLE
        })
    }

}