package pl.students.szczepieniaapp.presentation.ui.login

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.BuildConfig
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(

) : MyViewModel(), AdapterView.OnItemSelectedListener {

    private val _rules = MutableLiveData<ArrayList<String>>()
    val rules: LiveData<ArrayList<String>> get() = _rules


    fun goToPatientFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_patientFragment)
    }

    fun goToDriverFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_driverFragment)
    }

    fun getVersion(): String {
        return if (BuildConfig.DEBUG) "${BuildConfig.VERSION_NAME}.${BuildConfig.BUILD_TYPE} (${BuildConfig.VERSION_CODE})" else "${BuildConfig.VERSION_NAME})"
    }

    fun fetchRules(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Zaloguj się jako:")
        data.add("Pacjent")
        data.add("Lekarz")
        data.add("Operator logistyczny")
        data.add("Kierownik placówki")
        data.add("Operator NFZ")
        data.add("Kierowca")
        return data
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(LoginViewModel::class.java.name, "selected: $position")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(LoginViewModel::class.java.name, "onNothingSelected:")
    }

}