package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.OrderListFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.viewmodel.OrderListViewModel

@AndroidEntryPoint
class OrderListFragment : MyFragment<OrderListFragmentBinding>(){

    private val viewModel : OrderListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OrderListFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }
}