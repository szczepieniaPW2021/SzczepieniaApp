package pl.students.szczepieniaapp.presentation.ui.activities

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.ActivityMainBinding
import pl.students.szczepieniaapp.presentation.MyActivity


@AndroidEntryPoint
class MainActivity : MyActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectivityManager.isNetworkAvailable.observe(this, {
            if (it) binding.noConnectionBanner.visibility = View.GONE else binding.noConnectionBanner.visibility = View.VISIBLE
        })
    }

}