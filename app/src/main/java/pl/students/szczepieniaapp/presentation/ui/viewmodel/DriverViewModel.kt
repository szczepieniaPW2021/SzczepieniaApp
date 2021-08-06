package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.DriverFragment
import pl.students.szczepieniaapp.presentation.ui.listener.DriverListener
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

    private var callback: DriverListener = DriverFragment()

    private val _myRoute = MutableLiveData<MyRoute>()
    val myRoute: LiveData<MyRoute> get() = _myRoute

    private val _myPosition = MutableLiveData<LatLng>()
    val myPosition: LiveData<LatLng> get() = _myPosition

    private val _destination = MutableLiveData<LatLng>()
    val destination: LiveData<LatLng> get() = _destination

    private val _initLoading = MutableLiveData<Boolean>()
    val initLoading: LiveData<Boolean> get() = _initLoading

    var mMap: GoogleMap? = null

    fun getDestination(start: LatLng, view: View){

        _myPosition.postValue(start)

        _initLoading.postValue(true)
        EspressoIdlingResource.increment()
        useCaseFactory.getDestinationCoordinatesUseCase
            .execute()
            .subscribeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                _destination.postValue(it)
            }
            .flatMap {
                useCaseFactory.getGoogleMapRouteUseCase.execute(
                    "${start.latitude}, ${start.longitude}",
                    "${destination.value!!.latitude}, ${destination.value!!.longitude}",
                    context.value?.resources?.getString(R.string.google_maps_key)!!
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : SingleObserver<MyRoute>{
                override fun onSubscribe(d: Disposable?) {

                }

                override fun onError(e: Throwable?) {
                    //todo add toast message or snackbar
                    //Snackbar snackbar = Snackbar
                    //        .make(coordinatorLayout, "www.journaldev.com", Snackbar.LENGTH_LONG);
                    //snackbar.show();
                    _initLoading.postValue(false)
                    callback.displaySnackbar(view)
                    Log.d(DriverViewModel::class.java.simpleName, "onError: $e")
                }

                override fun onSuccess(value: MyRoute?) {
                    _myRoute.postValue(value)
                    _initLoading.postValue(false)
                    Log.d(DriverViewModel::class.java.simpleName, "Successfully got route: $value")
                    EspressoIdlingResource.decrement()
                }
            })
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

    fun drawRoute() {
        mMap?.addPolyline(drawPolyline(myRoute.value?.points!!))
    }

    fun setMarkersForMap() {
        mMap!!.addMarker(
            MarkerOptions().position(myPosition.value!!)
                .title(context?.value?.resources?.getString(R.string.my_position_map_text))
        )
        mMap!!.addMarker(
            MarkerOptions().position(destination.value!!)
                .title(context?.value?.resources?.getString(R.string.destination_map_text))
        )

        val builder = LatLngBounds.Builder()
        builder.include(myPosition.value!!)
        builder.include(destination.value!!)

        val padding = 75
        val bounds = builder.build()

        mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
    }

}