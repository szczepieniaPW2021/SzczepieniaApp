package pl.students.szczepieniaapp.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyViewModel
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel
@Inject
constructor(

) : MyViewModel() {

    private val _order = MutableLiveData<ReceivedOrder>()
    val order: LiveData<ReceivedOrder> get() = _order

    private val _orderId = MutableLiveData<Int>()
    val orderId: LiveData<Int> get() = _orderId

    fun setOrder(order: ReceivedOrder) {
        _order.postValue(order)
    }

    fun getOrder(id: Int) {
        _orderId.postValue(id)
    }

}