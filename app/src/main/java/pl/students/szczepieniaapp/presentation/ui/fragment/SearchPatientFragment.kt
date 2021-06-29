package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.SearchPatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment

@AndroidEntryPoint
class SearchPatientFragment : MyFragment<SearchPatientFragmentBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchPatientFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}