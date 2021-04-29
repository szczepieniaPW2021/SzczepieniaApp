package pl.students.szczepieniaapp.presentation.ui.patient

import android.view.View
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class PatientViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel() {

    private var callback: PatientListener = PatientFragment()

    val code: String = "123456789/ABCDEFRHIJK"          //to be removed

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