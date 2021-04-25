package pl.students.szczepieniaapp.presentation.ui.driver

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_NAVIGATION
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_PACKAGE

class DriverViewModel
@ViewModelInject
constructor(

): MyViewModel() {

    val _findingRoute = MutableLiveData<Boolean>()
    val findingRoute: LiveData<Boolean> get() = _findingRoute

    val _duration = MutableLiveData<String>()
    val duration: LiveData<String> get() = _duration

    val _distance = MutableLiveData<String>()
    val distance: LiveData<String> get() = _distance

    fun getDistanceAsString(value: String): String {
        return context.value!!.resources!!.getString(R.string.distance_map_text).format(value)
    }

    fun getTimeAsString(value: String): String {
        return context.value!!.resources!!.getString(R.string.duration_map_text).format(value)
    }

    fun goToGoogleMapNav(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_MAPS_NAVIGATION.format("50.06143", "19.93658")))
        intent.setPackage(GOOGLE_MAPS_PACKAGE)
        if (intent.resolveActivity(context.value!!.packageManager) != null){
            context.value!!.startActivity(intent)
        }
    }

}