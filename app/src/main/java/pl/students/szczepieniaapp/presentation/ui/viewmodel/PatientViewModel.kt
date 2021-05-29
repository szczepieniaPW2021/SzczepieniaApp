package pl.students.szczepieniaapp.presentation.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.students.szczepieniaapp.presentation.MyViewModel
import pl.students.szczepieniaapp.usecase.UseCaseFactory
import javax.inject.Inject

@HiltViewModel
class PatientViewModel
@Inject
constructor(
    private val useCaseFactory: UseCaseFactory
) : MyViewModel() {



}