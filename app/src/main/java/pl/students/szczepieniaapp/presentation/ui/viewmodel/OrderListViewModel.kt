package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.database.model.OrderEntity
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel() {

    private val _orders = MutableLiveData<List<OrderEntity>?>()
    val orders: LiveData<List<OrderEntity>?> get() = _orders

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

}