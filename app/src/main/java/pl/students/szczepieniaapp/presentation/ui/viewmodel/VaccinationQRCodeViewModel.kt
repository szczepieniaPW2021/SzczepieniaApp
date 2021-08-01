package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.presentation.ui.fragment.VaccinationQRCodeFragment
import pl.students.szczepieniaapp.presentation.ui.listener.VaccinationQRCodeListener
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class VaccinationQRCodeViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel() {

    private var callback: VaccinationQRCodeListener = VaccinationQRCodeFragment()

    private val _QRCodeDialogLoading = MutableLiveData<Boolean>()
    val QRCodeDialogLoading: LiveData<Boolean> get() = _QRCodeDialogLoading

    private val _displayDialog = MutableLiveData<Bitmap>()
    val displayDialog: LiveData<Bitmap> get() = _displayDialog

    init {
        _QRCodeDialogLoading.postValue(false)
    }

    fun showQRCodePopup(view: View) {

        useCaseFactory.getDataForQRCodeUseCase
            .execute()
            .onEach { dataState ->

                _QRCodeDialogLoading.postValue(dataState.loading)

                dataState.data?.let { string ->
                    var qrBits = useCaseFactory.getQRCodeUseCase.execute(string)
                    _displayDialog.postValue(qrBits)
                }

            }.launchIn(GlobalScope)

    }
}

