package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.OrderListFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.adapter.ReceivedOrderAdapter
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
        viewModel.apply {

            orderLoading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.orderListProgressBar.visibility = View.VISIBLE
                    binding.ordersLinearLayout.visibility = View.GONE
                } else {
                    binding.orderListProgressBar.visibility = View.GONE
                    binding.ordersLinearLayout.visibility = View.VISIBLE
                }
            }

            orders.observe(viewLifecycleOwner) { receivedOrder ->
                binding.receivedOrderRecycler.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = ReceivedOrderAdapter(receivedOrder!!, this)
                }
                binding.receivedOrderRecycler.visibility = View.VISIBLE
                binding.receivedOrderRecycler.adapter?.notifyDataSetChanged()
                binding.receivedOrderRecycler.scrollToPosition(receivedOrder!!.size-1)
            }
        }

        return binding.root
    }
}