package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.widget.NestedScrollView
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.MyRoute
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.DriverFragment
import pl.students.szczepieniaapp.presentation.ui.listener.DriverListener
import pl.students.szczepieniaapp.presentation.util.DriversNameMapper
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_NAVIGATION
import pl.students.szczepieniaapp.util.Constants.GOOGLE_MAPS_PACKAGE
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class DriverViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory,
    private val driversNameMapper: DriversNameMapper
): MyViewModel(), AdapterView.OnItemSelectedListener {

    private var callback: DriverListener = DriverFragment()

    private val _myRoute = MutableLiveData<MyRoute>()
    val myRoute: LiveData<MyRoute> get() = _myRoute

    private val _myPosition = MutableLiveData<LatLng>()
    val myPosition: LiveData<LatLng> get() = _myPosition

    private val _destination = MutableLiveData<LatLng>()
    val destination: LiveData<LatLng> get() = _destination

    private val _initLoading = MutableLiveData<Boolean>()
    val initLoading: LiveData<Boolean> get() = _initLoading

    private val _isBtnEnabled = MutableLiveData<Boolean>()
    val isBtnEnabled: LiveData<Boolean> get() = _isBtnEnabled

    private val _loadingRoute = MutableLiveData<Boolean>()
    val loadingRoute: LiveData<Boolean> get() = _loadingRoute

    private var driverId: Int = 0

    private val _driversNames = MutableLiveData<ArrayList<String>>()
    val driversNames: LiveData<ArrayList<String>> get() = _driversNames

    var mMap: GoogleMap? = null

    init {
        _initLoading.postValue(true)
        _isBtnEnabled.postValue(false)
        _loadingRoute.postValue(false)
        getAllUnavailableDrivers()
    }

    private fun getAllUnavailableDrivers(){

        useCaseFactory.getAllUnavailableDriversUseCase
            .execute()
            .onEach { dataState ->

                _initLoading.postValue(dataState.loading)

                dataState.data?.let {data ->
                    var names = driversNameMapper.mapToDomainModelList(data) as ArrayList<String>
                    names.add(0, context.value!!.getString(R.string.driver_manager_fragment_select_driver_text))
                    _driversNames.postValue(names)
                }

            }.launchIn(GlobalScope)
    }

    fun setCurrentPosition(){
        mMap!!.addMarker(
            MarkerOptions().position(myPosition.value!!)
                .title(context?.value?.resources?.getString(R.string.my_position_map_text))
        )

        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(myPosition.value!!))
    }

    fun setMyPosition(start: LatLng){
        _myPosition.postValue(start)
    }

    private fun getRoute(id: Int, view: View){
        GlobalScope.launch {
            _loadingRoute.postValue(true)
            var order = useCaseFactory.getOrderByDriverIdUseCase.execute(id)
            if (order.latitude != null && order.longitude != null) {
                _destination.postValue(LatLng(order.latitude!!, order.longitude!!))
                var route: MyRoute? =  useCaseFactory.getGoogleMapRouteUseCase.execute(

                    "${myPosition.value!!.latitude}, ${myPosition.value!!.longitude}",
                    "${order.latitude}, ${order.longitude}",
                    context.value?.resources?.getString(R.string.google_maps_key)!!

                )
                if (route != null) {
                    _myRoute.postValue(route)
                } else {
                    callback.displaySnackbar(view)
                }
            }

            _loadingRoute.postValue(false)
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

    fun selectDriver(view: View){
        if (driverId > 0) {
            getRoute(driverId, view)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(DriverViewModel::class.java.simpleName, "onItemSelected: " + parent!!.adapter.getItem(position))
        when (parent!!.adapter.getItem(position) as String) {

            context.value!!.getString(R.string.driver_manager_fragment_select_driver_text) -> {
                _isBtnEnabled.postValue(false)
                driverId = 0
            }

            else -> {
                driverId = parent!!.adapter.getItem(position).toString().substringBefore(".").toInt()
                _isBtnEnabled.postValue(true)
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(DriverViewModel::class.java.simpleName, "onNothingSelected: ")
    }

    fun scrollToBottom(scrollView: NestedScrollView) {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }

}