package pl.students.szczepieniaapp.presentation.ui.driver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.DriverFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment

@AndroidEntryPoint
class DriverFragment : MyFragment<DriverFragmentBinding>() {

    private val viewModel : DriverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectContext(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DriverFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }
}