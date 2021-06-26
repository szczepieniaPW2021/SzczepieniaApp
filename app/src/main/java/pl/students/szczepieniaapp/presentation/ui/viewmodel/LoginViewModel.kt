package pl.students.szczepieniaapp.presentation.ui.viewmodel

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
import pl.students.szczepieniaapp.presentation.ui.fragment.LoginFragment
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(

) : MyViewModel(), AdapterView.OnItemSelectedListener {

    private var navAction: Int? = null
    private var callback: LoginFragment = LoginFragment()

    private val _roles = MutableLiveData<ArrayList<String>>()
    val roles: LiveData<ArrayList<String>> get() = _roles

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> get() = _isButtonEnabled

    fun goToFragment(view: View) {
        navAction?.let { Navigation.findNavController(view).navigate(it) }
    }

    fun getVersion(): String {
        return if (BuildConfig.DEBUG) "${BuildConfig.VERSION_NAME}.${BuildConfig.BUILD_TYPE} (${BuildConfig.VERSION_CODE})" else "${BuildConfig.VERSION_NAME})"
    }

    fun fetchRoles(): ArrayList<String> {
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
        Log.d(LoginViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")
        fetchNavigationItem(parent!!.adapter.getItem(position) as String, view)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(LoginViewModel::class.java.name, "onNothingSelected:")
    }

    private fun fetchNavigationItem(item: String, view: View?) {
        when (item) {

            "Zaloguj się jako:" -> _isButtonEnabled.postValue(false)

            "Pacjent" -> {
                navAction = R.id.action_loginFragment_to_patientActivity
                _isButtonEnabled.postValue(true)
            }

            "Kierowca" -> {
                navAction = R.id.action_loginFragment_to_driverFragment
                _isButtonEnabled.postValue(true)
            }

            "Lekarz" -> {
                navAction = R.id.action_loginFragment_to_doctorActivity
                _isButtonEnabled.postValue(true)
            }

            else -> {
                Log.d(LoginViewModel::class.java.name, "Unrecognized selection")
                callback.toastMessage(
                    view!!,
                    view.context.resources.getString(R.string.selection_not_implemented_text)
                )
                _isButtonEnabled.postValue(false)
            }
        }
    }

}