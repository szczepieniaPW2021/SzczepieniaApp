package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.PatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.viewmodel.PatientViewModel

@AndroidEntryPoint
class PatientFragment : MyFragment<PatientFragmentBinding>() {

    private val viewModel : PatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PatientFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

}