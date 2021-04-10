package pl.students.szczepieniaapp.presentation.ui.login

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.navigation.Navigation
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel

class LoginViewModel
@ViewModelInject
constructor(

) : MyViewModel() {

    fun goToPatientFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_patientFragment)
    }
}