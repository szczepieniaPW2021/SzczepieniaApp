package pl.students.szczepieniaapp.presentation.ui.fragment


import androidx.appcompat.app.AlertDialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.VaccineOrderFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapter
import pl.students.szczepieniaapp.presentation.ui.listener.VaccineOrderListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.VaccineOrderViewModel
import java.util.*

@AndroidEntryPoint
class VaccineOrderFragment : MyFragment<VaccineOrderFragmentBinding>(), VaccineOrderListener {

    private val viewModel : VaccineOrderViewModel by viewModels()

    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VaccineOrderFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        viewModel.apply {
            orderNumberData.observe(viewLifecycleOwner) {
                binding.passengerNumberTextView.text = it.toString()
            }

            initialLoading.observe(viewLifecycleOwner){
                if (it) {
                    binding.addItemLinearLayoutProgressBar.visibility = View.VISIBLE
                    binding.addItemLinearLayout.visibility = View.GONE
                } else {
                    binding.addItemLinearLayoutProgressBar.visibility = View.GONE
                    binding.addItemLinearLayout.visibility = View.VISIBLE
                }
            }

            displayOrderList.observe(viewLifecycleOwner) {
                if (it) {
                    binding.orderListLinearLayout.visibility = View.VISIBLE
                    binding.additionalDataLinearLayout.visibility = View.VISIBLE
                    binding.makeOrderBtn.visibility = View.VISIBLE
                } else {
                    binding.orderListLinearLayout.visibility = View.INVISIBLE
                    binding.additionalDataLinearLayout.visibility = View.INVISIBLE
                    binding.makeOrderBtn.visibility = View.INVISIBLE
                }
            }

            vaccineTypes.observe(viewLifecycleOwner) {
                setSpinner(it as List<Objects>, binding.selectVaccineSpinner)
            }

            orderItems.observe(viewLifecycleOwner, { orders ->
                binding.orderRecycler.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = OrderAdapter(orders, this)
                }
                binding.orderRecycler.visibility = View.VISIBLE
                binding.orderRecycler.adapter?.notifyDataSetChanged()
                viewModel.scrollToBottom(binding.scrollView)
            })

            address.observe(viewLifecycleOwner) {
                binding.makeOrderBtn.isEnabled = enableMakeOrderBtn()
            }

            postalCode.observe(viewLifecycleOwner) {
                binding.makeOrderBtn.isEnabled = enableMakeOrderBtn()
            }

            city.observe(viewLifecycleOwner) {
                binding.makeOrderBtn.isEnabled = enableMakeOrderBtn()
            }

            deliveryDate.observe(viewLifecycleOwner) {
                binding.makeOrderBtn.isEnabled = enableMakeOrderBtn()
                binding.deliveryDateTextView.text = it
            }

            binding.selectVaccineSpinner.onItemSelectedListener = this

            viewModel.getFragmentManager(childFragmentManager)
        }

        shareDataViewModel.visitDate.observe(viewLifecycleOwner) {
            viewModel._deliveryDate.postValue(it)
        }

        return  binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectContext(activity)
    }

    private fun setSpinner(list: List<Objects>, spinner: Spinner) {
        val spinner: Spinner = spinner
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            list
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun toastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setDialog(view: View, string: String) {
        dialogBuilder = AlertDialog.Builder(view.context)
        val newView = LayoutInflater.from(view.context).inflate(R.layout.register_dialog, null)
        val textView = newView.findViewById<TextView>(R.id.description)
        textView.text = string
        dialogBuilder.setView(newView)
        dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun dismissDialog() {
        dialog.dismiss()
    }

}