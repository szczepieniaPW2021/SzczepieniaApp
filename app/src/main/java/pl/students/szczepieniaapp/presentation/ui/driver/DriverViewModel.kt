package pl.students.szczepieniaapp.presentation.ui.driver

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.usecase.GetGoogleMapRouteUseCase
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_NAVIGATION
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_PACKAGE
import javax.inject.Inject

@HiltViewModel
class DriverViewModel
@Inject
constructor(
    private val useCase: GetGoogleMapRouteUseCase
): MyViewModel() {

    private val _findingRoute = MutableLiveData<Boolean>()
    val findingRoute: LiveData<Boolean> get() = _findingRoute

    private val _duration = MutableLiveData<String>()
    val duration: LiveData<String> get() = _duration

    private val _distance = MutableLiveData<String>()
    val distance: LiveData<String> get() = _distance

    private val _points = MutableLiveData<PolylineOptions>()
    val points: LiveData<PolylineOptions> get() = _points

    val _myPosition = MutableLiveData<LatLng>()
    val myPosition: LiveData<LatLng> get() = _myPosition

    private val _destination = MutableLiveData<LatLng>()
    val destination: LiveData<LatLng> get() = _destination

    init {
        _destination.postValue(LatLng(50.06143, 19.93658))      //later to be changed for coordinates coming from api
    }

    fun getGoogleMapRoute(start: LatLng) {

        GlobalScope.launch {

            val response =  useCase.execute("${start.latitude}, ${start.longitude}",
                "${destination.value!!.latitude}, ${destination.value!!.longitude}",
                context.value?.resources?.getString(R.string.google_maps_key)!!)

            _distance.postValue(response?.distance)
            _duration.postValue(response?.duration)
            _points.postValue(drawPolyline(response?.points))
            _findingRoute.postValue(true)

//            useCase.execute("${start.latitude}, ${start.longitude}",
//                "${destination.value!!.latitude}, ${destination.value!!.longitude}",
//                context.value?.resources?.getString(R.string.google_maps_key)!!)
//                .onEach { dataState ->
//                    dataState.data?.let { result ->
//                        Log.d("testuje", "getGoogleMapRoute: " + result)
//                        _distance.postValue(result.distance)
//                        _duration.postValue(result.duration)
//                        _points.postValue(drawPolyline(result.points))
//                        _findingRoute.postValue(true)
//                    }
//                }
        }
    }

    private fun drawPolyline(points: String?): PolylineOptions {
        return PolylineOptions()
            .addAll(PolyUtil.decode(points))
            .width(8f)
            .color(Color.RED)
    }

    fun getDistanceAsString(value: String): String {
        return context.value!!.resources!!.getString(R.string.distance_map_text).format(value)
    }

    fun getTimeAsString(value: String): String {
        return context.value!!.resources!!.getString(R.string.duration_map_text).format(value)
    }

    fun goToGoogleMapNav(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_MAPS_NAVIGATION.format("${destination.value!!.latitude}", "${destination.value!!.longitude}")))
        intent.setPackage(GOOGLE_MAPS_PACKAGE)
        if (intent.resolveActivity(context.value!!.packageManager) != null){
            context.value!!.startActivity(intent)
        }
    }

}