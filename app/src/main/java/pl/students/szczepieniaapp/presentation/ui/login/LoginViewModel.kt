package pl.students.szczepieniaapp.presentation.ui.login

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import pl.students.szczepieniaapp.R

class LoginViewModel
@ViewModelInject
constructor(

) : ViewModel() {

    fun goToPatientFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_patientFragment)
    }
}