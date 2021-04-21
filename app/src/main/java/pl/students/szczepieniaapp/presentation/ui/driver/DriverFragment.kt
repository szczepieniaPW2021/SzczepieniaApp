package pl.students.szczepieniaapp.presentation.ui.driver

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.directions.route.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.DriverFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import java.util.*


@AndroidEntryPoint
class DriverFragment : MyFragment<DriverFragmentBinding>(), OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, RoutingListener {

    private val viewModel : DriverViewModel by viewModels()
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var myPosition: LatLng
    private val destination: LatLng = LatLng(50.06143, 19.93658)    //later to be changed for coordinates coming from api
    private var mMap: GoogleMap? = null
    private var polylines: MutableList<Polyline>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DriverFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fetchLocation()
        binding.mapView.onCreate(savedInstanceState)
        return binding.root
    }

    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                myPosition = LatLng(it.latitude, it.longitude)
                findRoutes(myPosition, destination);
                binding.mapView.getMapAsync(this)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            mMap = googleMap
            mMap!!.addMarker(MarkerOptions().position(myPosition).title(context?.resources?.getString(R.string.my_position_map_text)))
            mMap!!.addMarker(MarkerOptions().position(destination).title(context?.resources?.getString(R.string.destination_map_text)))

            val builder = LatLngBounds.Builder()
            builder.include(myPosition)
            builder.include(destination)

            val padding = 75
            val bounds = builder.build()

            mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
        }
    }

    private fun findRoutes(start: LatLng?, end: LatLng?) {
        if (start == null || end == null) {
            Toast.makeText(requireContext(), resources.getString(R.string.location_not_fetched_map_text), Toast.LENGTH_LONG).show()
        } else {
            val routing = Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(true)
                .waypoints(start, end)
                .key(resources.getString(R.string.google_maps_key))
                .build()
            routing.execute()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView?.onLowMemory()
    }

    override fun onDestroy() {
        if (binding.mapView != null) binding.mapView?.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView?.onSaveInstanceState(outState)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        findRoutes(myPosition, destination)
    }

    override fun onRoutingFailure(e: RouteException?) {
        Toast.makeText(requireContext(), resources.getString(R.string.location_not_fetched_map_text), Toast.LENGTH_LONG).show()
    }

    override fun onRoutingStart() {
        Toast.makeText(requireContext(), resources.getString(R.string.finding_route_map_text), Toast.LENGTH_LONG).show()
    }

    override fun onRoutingSuccess(route: ArrayList<Route>?, shortestRouteIndex: Int) {

        polylines?.clear()

        val polyOptions = PolylineOptions()

        polylines = ArrayList<Polyline>()

        if (route != null) {
            for (i in route.indices) {
                if (i == shortestRouteIndex) {
                    polyOptions.color(ContextCompat.getColor(requireContext(), R.color.black))
                    polyOptions.width(9f)
                    polyOptions.addAll(route?.get(shortestRouteIndex)?.points)
                    val polyline: Polyline = mMap!!.addPolyline(polyOptions)
                    (polylines as ArrayList<Polyline>).add(polyline)
                }
            }
        }
    }

    override fun onRoutingCancelled() {
        findRoutes(myPosition, destination)
    }
}