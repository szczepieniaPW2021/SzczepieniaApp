package pl.students.szczepieniaapp.presentation.ui.driver

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.DriverFragmentBinding
import pl.students.szczepieniaapp.network.model.route.DirectionResponses
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.util.Constants.GOOGLE_APIS_BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Callback
import retrofit2.Response


@AndroidEntryPoint
class DriverFragment : MyFragment<DriverFragmentBinding>(), OnMapReadyCallback {

    private val viewModel : DriverViewModel by viewModels()
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var myPosition: LatLng
    private val destination: LatLng = LatLng(50.06143, 19.93658)    //later to be changed for coordinates coming from api
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DriverFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel

        viewModel.apply {
            findingRoute.observe(viewLifecycleOwner, {if (it) binding.navContainer.visibility = View.VISIBLE})
            distance.observe(viewLifecycleOwner, {binding.durationTextView.text = viewModel.getDistanceAsString(it)})
            duration.observe(viewLifecycleOwner, {binding.distanceTextView.text = viewModel.getTimeAsString(it)})
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
                myPosition = LatLng(it.latitude, it.longitude)
                myPosition = LatLng(52.06143, 17.93658)
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

            findRoutes(myPosition, destination)
        }
    }

    private fun findRoutes(start: LatLng?, end: LatLng?) {
        if (start == null || end == null) {
            Toast.makeText(requireContext(), resources.getString(R.string.location_not_fetched_map_text), Toast.LENGTH_LONG).show()
        } else {
            val apiServices = RetrofitClient.apiServices(requireContext())
            apiServices.getDirection("${start.latitude}, ${start.longitude}", "${end.latitude}, ${end.longitude}", getString(R.string.google_maps_key))
                .enqueue(object : Callback<DirectionResponses> {
                    override fun onResponse(call: Call<DirectionResponses>, response: Response<DirectionResponses>) {
                        drawPolyline(response)
                    }

                    override fun onFailure(call: Call<DirectionResponses>, t: Throwable) {
                        Toast.makeText(requireContext(), resources.getString(R.string.location_not_fetched_map_text), Toast.LENGTH_LONG).show()
                    }
                })
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

    private fun drawPolyline(response: Response<DirectionResponses>) {
        val shape = response.body()?.routes?.get(0)?.overviewPolyline?.points
        val polyline = PolylineOptions()
            .addAll(PolyUtil.decode(shape))
            .width(8f)
            .color(Color.RED)
        mMap?.addPolyline(polyline)
        viewModel._distance.postValue(response.body()?.routes?.get(0)?.legs?.get(0)?.distance?.text)
        viewModel._duration.postValue(response.body()?.routes?.get(0)?.legs?.get(0)?.duration?.text)
        viewModel._findingRoute.postValue(true)
    }

    private interface ApiServices {
        @GET("maps/api/directions/json")
        fun getDirection(@Query("origin") origin: String,
                         @Query("destination") destination: String,
                         @Query("key") apiKey: String): Call<DirectionResponses>
    }

    private object RetrofitClient {
        fun apiServices(context: Context): ApiServices {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GOOGLE_APIS_BASE_URL)
                .build()

            return retrofit.create<ApiServices>(ApiServices::class.java)
        }
    }
}