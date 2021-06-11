package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.activities.MainActivity
import pl.students.szczepieniaapp.presentation.ui.activities.SplashScreenActivity
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject
constructor(

): MyViewModel() {

    private val SPLASH_SCREEN = 5000L

    fun navigateToMainActivity(activity: SplashScreenActivity) {
        GlobalScope.launch(Dispatchers.Main) {
            delay(SPLASH_SCREEN)
            useIntentToNavigate(activity)
        }
    }

    private fun useIntentToNavigate(activity: SplashScreenActivity) {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(activity, intent, null)
        activity.finish()
    }

}