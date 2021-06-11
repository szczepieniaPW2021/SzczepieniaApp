package pl.students.szczepieniaapp.presentation.ui.activities

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.ActivitySplashScreenBinding
import pl.students.szczepieniaapp.presentation.ui.viewmodel.SplashScreenViewModel


class SplashScreenActivity : AppCompatActivity() {

    private val viewModel : SplashScreenViewModel by viewModels()
    private lateinit var binding: ActivitySplashScreenBinding
    lateinit var topAnim : Animation
    lateinit var bottomAnim : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        binding.imageView.animation = topAnim
        binding.appNameTextView.animation = bottomAnim
        viewModel.navigateToMainActivity(this)
    }

}