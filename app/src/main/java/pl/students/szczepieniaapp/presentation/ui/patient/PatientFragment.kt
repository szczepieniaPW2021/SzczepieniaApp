package pl.students.szczepieniaapp.presentation.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.PatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment

@AndroidEntryPoint
class PatientFragment : MyFragment<PatientFragmentBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PatientFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}