package pl.students.szczepieniaapp.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.ActivityDoctorBinding
import pl.students.szczepieniaapp.databinding.ActivityFacilityManagerBinding

@AndroidEntryPoint
class FacilityManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFacilityManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacilityManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object{
        fun buildToastMessage(string: String): String{
            return "$string"
        }
    }
}