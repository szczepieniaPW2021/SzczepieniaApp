package pl.students.szczepieniaapp.presentation.ui.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.ActivityOperatorBinding
import pl.students.szczepieniaapp.presentation.MyActivity

@AndroidEntryPoint
class OperatorActivity : MyActivity<ActivityOperatorBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOperatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.operatorBottomNav.setupWithNavController(Navigation.findNavController(this, R.id.activityOperatorFragmentContainerView))

        connectivityManager.isNetworkAvailable.observe(this, {
            if (it) binding.noConnectionBanner.visibility = View.GONE else binding.noConnectionBanner.visibility = View.VISIBLE
        })
    }
}