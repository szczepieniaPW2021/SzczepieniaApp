package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.adapter.ReceivedOrderAdapterListener
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel(), ReceivedOrderAdapterListener {

    private val _orders = MutableLiveData<List<ReceivedOrder>?>()
    val orders: LiveData<List<ReceivedOrder>?> get() = _orders

    private val _orderLoading = MutableLiveData<Boolean>()
    val orderLoading: LiveData<Boolean> get() = _orderLoading

    init {
        getAllOrders()
    }

    private fun getAllOrders(){

        useCaseFactory.getAllOrdersUseCase
            .execute()
            .onEach { dataState ->

                _orderLoading.postValue(dataState.loading)

                dataState.data?.let { list ->
                    _orders.postValue(list)
                }

            }.launchIn(GlobalScope)
    }

    override fun clickItem(view: View, order: ReceivedOrder) {
        val bundle = bundleOf("integer" to order.id)
        Navigation.findNavController(view).navigate(R.id.action_orderListFragment_to_orderDetailsFragment, bundle)
    }

}