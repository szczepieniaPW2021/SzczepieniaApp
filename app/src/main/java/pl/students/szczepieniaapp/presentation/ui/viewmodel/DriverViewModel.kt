package pl.students.szczepieniaapp.presentation.ui.viewmodel

import pl.students.szczepieniaapp.BuildConfig
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.util.EspressoIdlingResource
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_NAVIGATION
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_PACKAGE
import javax.inject.Inject

@HiltViewModel
class DriverViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
): MyViewModel() {

    private val _myRoute = MutableLiveData<MyRoute>()
    val myRoute: LiveData<MyRoute> get() = _myRoute

    val _myPosition = MutableLiveData<LatLng>()
    val myPosition: LiveData<LatLng> get() = _myPosition

    private val _destination = MutableLiveData<LatLng>()
    val destination: LiveData<LatLng> get() = _destination

    init {
        _destination.postValue(LatLng(BuildConfig.Lat, BuildConfig.Lng))      //later to be changed for coordinates coming from api
    }

    fun getGoogleMapRoute(start: LatLng) {
        EspressoIdlingResource.increment()
        useCaseFactory.getGoogleMapRouteUseCase
                .execute(
                    "${start.latitude}, ${start.longitude}",
                    "${destination.value!!.latitude}, ${destination.value!!.longitude}",
                    context.value?.resources?.getString(R.string.google_maps_key)!!
                ).onEach { dataState ->

                dataState.data?.let { route ->
                    _myRoute.postValue(route)
                    EspressoIdlingResource.decrement()
                }

            }.launchIn(GlobalScope)
    }

    fun drawPolyline(points: String?): PolylineOptions {
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

    fun getArrivalAddressAsString(value: String): String {
        return context.value!!.resources!!.getString(R.string.arrival_address_map_text).format(value)
    }

    fun getDepartureAddressAsString(value: String): String {
        return context.value!!.resources!!.getString(R.string.departure_address_map_text).format(value)
    }

    fun goToGoogleMapNav(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_MAPS_NAVIGATION.format("${destination.value!!.latitude}", "${destination.value!!.longitude}")))
        intent.setPackage(GOOGLE_MAPS_PACKAGE)
        if (intent.resolveActivity(context.value!!.packageManager) != null){
            context.value!!.startActivity(intent)
        }
    }

}