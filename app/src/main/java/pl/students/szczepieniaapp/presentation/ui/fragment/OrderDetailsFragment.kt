package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.FragmentOrderDetailsBinding
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.viewmodel.OrderDetailsViewModel

@AndroidEntryPoint
class OrderDetailsFragment : MyFragment<FragmentOrderDetailsBinding>() {

    private val viewModel : OrderDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("integer").let {
            if (it != null) {
                viewModel.getOrder(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        viewModel.apply {
            order.observe(viewLifecycleOwner){
                Log.d("testuje", "onCreateView: " + it)
            }
        }
        return binding.root
    }

}