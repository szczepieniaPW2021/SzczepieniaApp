package pl.students.szczepieniaapp.presentation.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
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
import pl.students.szczepieniaapp.presentation.ui.viewmodel.DriverViewModel


@AndroidEntryPoint
class DriverFragment : MyFragment<DriverFragmentBinding>(), OnMapReadyCallback {

    private val viewModel : DriverViewModel by viewModels()
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DriverFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel

        viewModel.apply {
            myRoute.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.durationTextView.text = viewModel.getDistanceAsString(it.duration!!)
                    binding.distanceTextView.text = viewModel.getTimeAsString(it.distance!!)
                    binding.endAddressTextView.text = viewModel.getArrivalAddressAsString(it.endAddress!!)
                    binding.startAddressTextView.text = viewModel.getDepartureAddressAsString(it.startAddress!!)
                    mMap?.addPolyline(viewModel.drawPolyline(it.points!!))
                    binding.navContainer.visibility = View.VISIBLE
                    binding.navBtn.visibility = View.VISIBLE
                }
            }

        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fetchLocation()
        binding.mapView.onCreate(savedInstanceState)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectContext(activity)
    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        task.addOnSuccessListener {
            if (it != null) {
                viewModel._myPosition.postValue(LatLng(it.latitude, it.longitude))
                viewModel.getGoogleMapRoute(LatLng(it.latitude, it.longitude))
                binding.mapView.getMapAsync(this)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.apply {
            mMap = googleMap
            mMap!!.addMarker(MarkerOptions().position(viewModel.myPosition.value!!).title(context?.resources?.getString(R.string.my_position_map_text)))
            mMap!!.addMarker(MarkerOptions().position(viewModel.destination.value!!).title(context?.resources?.getString(R.string.destination_map_text)))

            val builder = LatLngBounds.Builder()
            builder.include(viewModel.myPosition.value!!)
            builder.include(viewModel.destination.value!!)

            val padding = 75
            val bounds = builder.build()

            mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
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
}