package pl.students.szczepieniaapp.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MyViewModel : ViewModel() {

    private val _context = MutableLiveData<Context>()
    protected val context: LiveData<Context> get() = _context

    fun selectContext(context: Context?) {
        _context.value = context
    }
}