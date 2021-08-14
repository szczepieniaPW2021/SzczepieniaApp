package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.Order
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapterListener
import pl.students.szczepieniaapp.presentation.ui.fragment.OrderDetailsFragment
import pl.students.szczepieniaapp.presentation.ui.listener.OrderDetailsListener
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel(), OrderAdapterListener {

    private var callback: OrderDetailsListener = OrderDetailsFragment()

    private val _order = MutableLiveData<ReceivedOrder>()
    val order: LiveData<ReceivedOrder> get() = _order

    private val _orderLoading = MutableLiveData<Boolean>()
    val orderLoading: LiveData<Boolean> get() = _orderLoading

    private val disposable = CompositeDisposable()

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

    fun sendOrder(view: View) {

        callback.setDialog(view, view.context.getString(R.string.order_details_fragment_sending_order_text))

        disposable.add(
            useCaseFactory.getSendOrderUseCase.execute(
                order = order.value!!
                )
                .subscribeOn(Schedulers.io())
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

    fun getStatus() :String {
        val arrayStatus = context.value!!.resources.getStringArray(R.array.order_details_fragment_status)
        return "${arrayStatus[order.value!!.deliveryStatus.ordinal]}"
    }

    override fun removeItem(view: View, order: Order) {
        Log.d(OrderDetailsViewModel::class.simpleName, "removeItem: do nothing")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}