package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.students.szczepieniaapp.databinding.DoctorFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment

class DoctorFragment : MyFragment<DoctorFragmentBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DoctorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}