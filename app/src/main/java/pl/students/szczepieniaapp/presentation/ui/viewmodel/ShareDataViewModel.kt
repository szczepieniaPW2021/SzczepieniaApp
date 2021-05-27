package pl.students.szczepieniaapp.presentation.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.students.szczepieniaapp.presentation.MyViewModel

class ShareDataViewModel
@ViewModelInject
constructor(
) : MyViewModel() {

    val _visitTime = MutableLiveData<String>()
    val visitTime: LiveData<String> get() = _visitTime

}