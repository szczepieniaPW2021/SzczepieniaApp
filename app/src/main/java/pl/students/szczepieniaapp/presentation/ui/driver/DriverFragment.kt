package pl.students.szczepieniaapp.presentation.ui.driver

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.DriverFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pl.students.szczepieniaapp.R

@AndroidEntryPoint
class DriverFragment : MyFragment<DriverFragmentBinding>(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private val viewModel : DriverViewModel by viewModels()
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var myPosition: LatLng

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
                binding.mapView.getMapAsync(this)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            val myPositionMarkerOptions = MarkerOptions().position(myPosition).title(
                context?.resources?.getString(R.string.my_position_map_text)
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(myPosition))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12f))
            googleMap.addMarker(myPositionMarkerOptions)
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
        TODO("Not yet implemented")
    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }
}