package pl.students.szczepieniaapp.presentation

import androidx.fragment.app.Fragment

abstract class MyFragment<Binding> : Fragment() {

    protected var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}