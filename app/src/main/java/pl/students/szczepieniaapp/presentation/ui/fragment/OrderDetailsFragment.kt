package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.FragmentOrderDetailsBinding
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapter
import pl.students.szczepieniaapp.presentation.ui.viewmodel.OrderDetailsViewModel

@AndroidEntryPoint
class OrderDetailsFragment : MyFragment<FragmentOrderDetailsBinding>() {

    private val viewModel : OrderDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectContext(requireContext())
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

            orderLoading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.orderProgressBar.visibility = View.VISIBLE
                    binding.orderDataLinearLayout.visibility = View.GONE
                } else {
                    binding.orderProgressBar.visibility = View.GONE
                    binding.orderDataLinearLayout.visibility = View.VISIBLE
                }
            }

            order.observe(viewLifecycleOwner){ receivedOrder ->
                if (receivedOrder != null) {
                    binding.deliveryDateTextView.text = getDeliveryDate()
                    binding.orderDateTextView.text = getOrderDate()
                    binding.deliveryAddressTextView.text = getAddress()

                    binding.ordersListRecyclerView.also {
                        it.layoutManager = LinearLayoutManager(requireContext())
                        it.setHasFixedSize(true)
                        it.adapter = OrderAdapter(receivedOrder.orders!!, this, isRemoveItemVisible = false)
                    }
                    binding.ordersListRecyclerView.visibility = View.VISIBLE
                    binding.ordersListRecyclerView.adapter?.notifyDataSetChanged()
                    binding.orderDataLinearLayout.visibility = View.VISIBLE
                }
            }
        }
        return binding.root
    }

}