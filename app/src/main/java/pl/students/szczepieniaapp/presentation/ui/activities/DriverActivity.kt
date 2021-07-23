package pl.students.szczepieniaapp.presentation.ui.activities

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.ActivityDriverBinding
import pl.students.szczepieniaapp.presentation.MyActivity

@AndroidEntryPoint
class DriverActivity : MyActivity<ActivityDriverBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectivityManager.isNetworkAvailable.observe(this, {
            if (it) binding.noConnectionBanner.visibility = View.GONE else binding.noConnectionBanner.visibility = View.VISIBLE
        })
    }

}