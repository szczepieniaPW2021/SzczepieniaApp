package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.database.converter.ReceiveOrderStatus
import pl.students.szczepieniaapp.databinding.FragmentOrderDetailsBinding
import pl.students.szczepieniaapp.domain.model.ReceivedOrder
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapter
import pl.students.szczepieniaapp.presentation.ui.listener.OrderDetailsListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.OrderDetailsViewModel

@AndroidEntryPoint
class OrderDetailsFragment : MyFragment<FragmentOrderDetailsBinding>(), OrderDetailsListener {

    private val viewModel : OrderDetailsViewModel by viewModels()

    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

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
                    binding.statusTextView.text = getStatus()

                    binding.ordersListRecyclerView.also {
                        it.layoutManager = LinearLayoutManager(requireContext())
                        it.setHasFixedSize(true)
                        it.adapter = OrderAdapter(receivedOrder.orders!!, this, isRemoveItemVisible = false)
                    }
                    binding.ordersListRecyclerView.visibility = View.VISIBLE
                    binding.ordersListRecyclerView.adapter?.notifyDataSetChanged()
                    binding.orderDataLinearLayout.visibility = View.VISIBLE
                }

                if (receivedOrder.deliveryStatus != ReceiveOrderStatus.ORDERED) {
                    binding.sendOrderBtn.isEnabled = false
                }
            }
        }
        return binding.root
    }

    override fun setDialog(view: View, string: String) {
        dialogBuilder = AlertDialog.Builder(view.context)
        val newView = LayoutInflater.from(view.context).inflate(R.layout.register_dialog, null)
        val textView = newView.findViewById<TextView>(R.id.description)
        textView.text = string
        dialogBuilder.setView(newView)
        dialog = dialogBuilder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    override fun dismissDialog() {
        dialog.dismiss()
    }

    override fun toastMessage(view: View, string: String) {
        Toast.makeText(view.context, string, Toast.LENGTH_LONG).show()
    }

}