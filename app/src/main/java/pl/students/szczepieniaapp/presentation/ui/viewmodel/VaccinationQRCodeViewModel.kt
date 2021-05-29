package pl.students.szczepieniaapp.presentation.ui.viewmodel

import android.view.View
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
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

    val code: String = "123456789/ABCDEFRHIJK"          //TODO to be removed

    fun showQRCodePopup(view: View) {

        useCaseFactory.getQRCodeUseCase
            .execute(code)
            .onEach { dataState ->

                dataState.data?.let { qrBits ->
                    callback.setProgressDialog(view, qrBits)
                }

            }.launchIn(viewModelScope)

    }
}

