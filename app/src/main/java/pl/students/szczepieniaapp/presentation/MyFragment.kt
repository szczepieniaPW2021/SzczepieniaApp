package pl.students.szczepieniaapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import pl.students.szczepieniaapp.presentation.ui.viewmodel.ShareDataViewModel

abstract class MyFragment<Binding> : Fragment() {

    protected var _binding: Binding? = null
    protected val binding get() = _binding!!

    protected val shareDataViewModel: ShareDataViewModel by activityViewModels()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}