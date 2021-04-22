package pl.students.szczepieniaapp.presentation.ui.driver

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_NAVIGATION
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_PACKAGE

class DriverViewModel
@ViewModelInject
constructor(

): MyViewModel() {

    val _findingRoute = MutableLiveData<Boolean>()
    val findingRoute: LiveData<Boolean> get() = _findingRoute

    init {
        _findingRoute.postValue(false)
    }

    fun goToGoogleMapNav(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_MAPS_NAVIGATION.format(50.06143, 19.93658)))
        intent.setPackage(GOOGLE_MAPS_PACKAGE)
        if (intent.resolveActivity(context.value!!.packageManager) != null){
            context.value!!.startActivity(intent)
        }
    }

}