package pl.students.szczepieniaapp.presentation.ui.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.ActivityPatientBinding
import pl.students.szczepieniaapp.presentation.MyActivity

@AndroidEntryPoint
class PatientActivity : MyActivity<ActivityPatientBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.patientBottomNav.setupWithNavController(Navigation.findNavController(this, R.id.activityPatientFragmentContainerView))

        connectivityManager.isNetworkAvailable.observe(this, {
            if (it) binding.noConnectionBanner.visibility = View.GONE else binding.noConnectionBanner.visibility = View.VISIBLE
        })
    }

}