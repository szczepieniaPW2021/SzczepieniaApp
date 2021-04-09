package pl.students.szczepieniaapp.presentation.ui.patient

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.navigation.Navigation
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel

class PatientViewModel
@ViewModelInject
constructor(

) : MyViewModel() {

    private var callback: PatientListener = PatientFragment()

    fun showQRCodePopup(view: View) {
        callback.setProgressDialog(view)
    }

}