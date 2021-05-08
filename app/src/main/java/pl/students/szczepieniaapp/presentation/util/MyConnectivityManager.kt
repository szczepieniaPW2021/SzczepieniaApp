package pl.students.szczepieniaapp.presentation.util

import android.app.Application
import javax.inject.Inject
import javax.inject.Singleton
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@Singleton
class MyConnectivityManager
@Inject
constructor(
    application: Application,
){

    private val connectionLiveData = ConnectionLiveData(application)

    // observe this in ui
    private val _isNetworkAvailable = MutableLiveData(false)
    val isNetworkAvailable: LiveData<Boolean> get() = _isNetworkAvailable

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.observe(lifecycleOwner, { isConnected ->
            isConnected?.let { _isNetworkAvailable.value = it }
        })
    }

    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner){
        connectionLiveData.removeObservers(lifecycleOwner)
    }
}