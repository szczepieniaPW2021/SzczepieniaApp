package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.domain.model.Order
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.VaccineOrderFragment
import pl.students.szczepieniaapp.presentation.ui.listener.VaccineOrderListener
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class VaccineOrderViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
): MyViewModel(), AdapterView.OnItemSelectedListener {

    private var callback: VaccineOrderListener = VaccineOrderFragment()

    private val _vaccineTypes = MutableLiveData<ArrayList<String>>()
    val vaccineTypes: LiveData<ArrayList<String>> get() = _vaccineTypes

    private val _orderItems = MutableLiveData<ArrayList<Order>>()
    val orderItems: LiveData<ArrayList<Order>> get() = _orderItems

    private val _passengersNumberData = MutableLiveData<Int>()
    val passengersNumberData: LiveData<Int> get() = _passengersNumberData

    private var vaccineType = ""
    private final var list = mutableListOf<Order>()

    init {
        _passengersNumberData.postValue(1)
        _vaccineTypes.postValue(fetchVaccineType())
    }

    private fun fetchVaccineType(): ArrayList<String> {
        val data: ArrayList<String> = arrayListOf()
        data.add("Select vaccine:")
        data.add("Astra Zeneca")
        data.add("Pfizer/BioNTech")
        data.add("Moderna")
        data.add("Johnson & Johnson")
        return data
    }

    fun onPassengersNumberIncClick(view: View) {
        if (passengersNumberData.value!! < 99){
            _passengersNumberData.postValue(passengersNumberData.value!! + 1)
        }
    }

    fun onPassengersNumberDecClick(view: View) {
        if (passengersNumberData.value!! > 1){
            _passengersNumberData.postValue(passengersNumberData.value!! - 1)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(VaccineOrderViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")
        selectVaccineType(parent!!.adapter.getItem(position) as String, view)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(VaccineOrderViewModel::class.java.name, "onNothingSelected:")
    }

    private fun selectVaccineType(item: String, view: View?) {
        vaccineType = when (item) {
            "Select vaccine:" -> { "" }
            else -> { item }
        }
    }

    fun addToOrder(view: View) {

        if (vaccineType.isNullOrEmpty()) {
            callback.toastMessage(view.context, view.context.resources.getString(R.string.vaccine_order_fragment_select_vaccine_warning_text))
            return
        }

        list.add(Order(vaccineType))
        _orderItems.postValue(list as ArrayList<Order>?)
    }

}