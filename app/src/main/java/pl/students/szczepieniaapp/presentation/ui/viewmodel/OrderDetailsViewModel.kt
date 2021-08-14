package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.Order
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapterListener
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel(), OrderAdapterListener {

    private val _order = MutableLiveData<ReceivedOrder>()
    val order: LiveData<ReceivedOrder> get() = _order

    private val _orderLoading = MutableLiveData<Boolean>()
    val orderLoading: LiveData<Boolean> get() = _orderLoading

    fun getOrder(id: Int) {

        useCaseFactory.getReceivedOrderByIdUseCase
            .execute(id = id)
            .onEach { dataState ->

                _orderLoading.postValue(dataState.loading)

                dataState.data?.let { order ->
                    _order.postValue(order)
                }
            }.launchIn(GlobalScope)

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

    override fun removeItem(view: View, order: Order) {
        Log.d(OrderDetailsViewModel::class.simpleName, "removeItem: do nothing")
    }

}