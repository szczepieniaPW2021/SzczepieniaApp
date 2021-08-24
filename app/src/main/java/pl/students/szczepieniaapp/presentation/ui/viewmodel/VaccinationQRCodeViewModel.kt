package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.database.model.PatientEntity
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.VaccinationQRCodeFragment
import pl.students.szczepieniaapp.presentation.ui.listener.VaccinationQRCodeListener
import pl.students.szczepieniaapp.presentation.util.PatientsNameMapper
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class VaccinationQRCodeViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory,
    private val patientsNameMapper: PatientsNameMapper
) : MyViewModel(), AdapterView.OnItemSelectedListener {

    private var callback: VaccinationQRCodeListener = VaccinationQRCodeFragment()

    private val _QRCodeDialogLoading = MutableLiveData<Boolean>()
    val QRCodeDialogLoading: LiveData<Boolean> get() = _QRCodeDialogLoading

    private val _displayDialog = MutableLiveData<Bitmap>()
    val displayDialog: LiveData<Bitmap> get() = _displayDialog

    private val _patientsName = MutableLiveData<ArrayList<String>>()
    val patientsName: LiveData<ArrayList<String>> get() = _patientsName

    private val _isButton = MutableLiveData<Boolean>()
    val isButton: LiveData<Boolean> get() = _isButton

    private var patients: List<PatientEntity>? = null
    private var selectedPatient: PatientEntity? = null

    init {
        _QRCodeDialogLoading.postValue(true)
        getAllPassengers()
    }

    private fun getAllPassengers(){

        useCaseFactory.getAllPatientsUseCase
            .execute()
            .onEach { dataState ->

                _QRCodeDialogLoading.postValue(dataState.loading)

                dataState.data?.let {list ->
                    _patientsName.postValue(patientsNameMapper.mapToDomainModelList(list) as ArrayList<String>)
                    patients = list
                }

            }.launchIn(GlobalScope)
    }

    fun showQRCodePopup(view: View) {
        var qrBits = useCaseFactory.getQRCodeUseCase.execute(selectedPatient!!.qrCode)
        _displayDialog.postValue(qrBits)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(VaccinationQRCodeViewModel::class.java.name, "selected: ${parent!!.adapter.getItem(position)}")
        getPatientFromPatients(parent!!.adapter.getItem(position) as String)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(VaccinationQRCodeViewModel::class.java.name, "onNothingSelected:")
    }

    private fun getPatientFromPatients(string: String){
        val name = string.substringBefore(" ")
        val lastName = string.substringAfter(" ")

        for (item in patients!!){
            if (item.name == name && item.lastName == lastName) selectedPatient = item
        }
    }
}

