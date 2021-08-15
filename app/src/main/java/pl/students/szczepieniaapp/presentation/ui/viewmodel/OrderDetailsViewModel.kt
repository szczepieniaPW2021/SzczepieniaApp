package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.database.model.DriverEntity
import pl.students.szczepieniaapp.database.model.PatientEntity
import pl.students.szczepieniaapp.domain.model.Order
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapterListener
import pl.students.szczepieniaapp.presentation.ui.fragment.OrderDetailsFragment
import pl.students.szczepieniaapp.presentation.ui.listener.OrderDetailsListener
import pl.students.szczepieniaapp.presentation.util.DriversNameMapper
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory,
    private val driversNameMapper: DriversNameMapper
) : MyViewModel(), OrderAdapterListener, AdapterView.OnItemSelectedListener {

    private var callback: OrderDetailsListener = OrderDetailsFragment()

    private val _order = MutableLiveData<ReceivedOrder>()
    val order: LiveData<ReceivedOrder> get() = _order

    private val _orderLoading = MutableLiveData<Boolean>()
    val orderLoading: LiveData<Boolean> get() = _orderLoading

    private val _driversNames = MutableLiveData<ArrayList<String>>()
    val driversNames: LiveData<ArrayList<String>> get() = _driversNames

    private var driverName: String = ""

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEnabled: LiveData<Boolean> get() = _isButtonEnabled

    private var drivers: List<DriverEntity>? = null

    private val disposable = CompositeDisposable()

    init {
        _isButtonEnabled.postValue(false)
    }

    fun getOrder(id: Int) {

        useCaseFactory.getAllAvailableDriversUseCase
            .execute()
            .onEach { dataState ->

                dataState.data?.let { data->
                    drivers = data
                    val names = driversNameMapper.mapToDomainModelList(data) as ArrayList<String>
                    names.add(0, context.value!!.getString(R.string.driver_manager_fragment_select_driver_text))
                    _driversNames.postValue(names)
                }

            }.launchIn(viewModelScope)

        useCaseFactory.getReceivedOrderByIdUseCase
            .execute(id = id)
            .onEach { dataState ->

                _orderLoading.postValue(dataState.loading)

                dataState.data?.let { order ->
                    _order.postValue(order)
                }
            }.launchIn(GlobalScope)

    }

    fun sendOrder(view: View) {

        callback.setDialog(view, view.context.getString(R.string.order_details_fragment_sending_order_text))

        disposable.add(
            useCaseFactory.getSendOrderUseCase.execute(
                order = order.value!!
                )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnComplete {
                    Log.d(OrderDetailsViewModel::class.simpleName, "Order was successfully send")
                }
                .andThen(
                    useCaseFactory.getMakeDriverUnavailableUseCase.execute(driverName)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnComplete { Action {
                    Log.d(OrderDetailsViewModel::class.simpleName, "Driver was successfully disabled")
                } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_orderDetailsFragment_to_orderListFragment)

                        callback.dismissDialog()
                        callback.toastMessage(
                            view,
                            view.context.resources.getString(R.string.order_details_fragment_order_send_successfully_text)
                        )
                    }

                    override fun onError(e: Throwable) {
                        callback.dismissDialog()
                    }

                })
        )
    }

    fun getDeliveryDate(): String {
        return "${context.value!!.getString(R.string.order_details_fragment_delivery_date_text)} ${order.value!!.deliveryDate}"
    }

    fun getOrderDate(): String {
        return "${context.value!!.getString(R.string.order_details_fragment_order_date_text)} ${order.value!!.orderDate}"
    }

    fun getAddress(): String {
        return "${context.value!!.getString(R.string.order_details_fragment_address_text)} ${order.value!!.street}, ${order.value!!.postalCode} ${order.value!!.city}"
    }

    fun getStatus(): String {
        val arrayStatus = context.value!!.resources.getStringArray(R.array.order_details_fragment_status)
        return "${arrayStatus[order.value!!.deliveryStatus.ordinal]}"
    }

    fun getDriver(): String {

        for (driver in drivers!!){
            if (order!!.value!!.driverId != null && order!!.value!!.driverId == driver.id) {
                return "${driver.name} ${driver.lastName}"
            }
        }
        return " "
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(OrderDetailsViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")

        when (parent!!.adapter.getItem(position)) {

            driversNames.value!![0] -> {
                driverName = ""
                isBtnEnabled()
            }

            else -> {
                driverName = parent!!.adapter.getItem(position) as String
                getDriverFromSpinner(driverName)
                isBtnEnabled()
            }
        }

    }

    private fun isBtnEnabled() {
        if (driverName.isNullOrEmpty() || getStatus() != ReceiveOrderStatus.ORDERED.name) {
            _isButtonEnabled.postValue(false)
        } else {
            _isButtonEnabled.postValue(true)
        }
    }

    private fun getDriverFromSpinner(name: String) {
        if (!driverName.isNullOrEmpty()) {

            for (driver in drivers!!) {
                if (driver.id == name.substringBefore(".").toInt()) {
                    var tmpOrder = order.value
                    tmpOrder!!.driverId = driver.id
                    _order.postValue(tmpOrder)
                }
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(OrderDetailsViewModel::class.java.name, "onNothingSelected:")
    }

    override fun removeItem(view: View, order: Order) {
        Log.d(OrderDetailsViewModel::class.simpleName, "removeItem: do nothing")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}