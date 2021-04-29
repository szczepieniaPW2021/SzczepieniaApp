package pl.students.szczepieniaapp.presentation.ui.login

import android.view.View
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(

) : MyViewModel() {

    fun goToPatientFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_patientFragment)
    }

    fun goToDriverFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_driverFragment)
    }
}