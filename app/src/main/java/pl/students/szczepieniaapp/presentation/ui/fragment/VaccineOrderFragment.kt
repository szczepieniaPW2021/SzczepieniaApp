package pl.students.szczepieniaapp.presentation.ui.fragment

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.VaccineOrderFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.adapter.OrderAdapter
import pl.students.szczepieniaapp.presentation.ui.listener.VaccineOrderListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.VaccineOrderViewModel
import java.util.*

@AndroidEntryPoint
class VaccineOrderFragment : MyFragment<VaccineOrderFragmentBinding>(), VaccineOrderListener {

    private val viewModel : VaccineOrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VaccineOrderFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        viewModel.apply {
            passengersNumberData.observe(viewLifecycleOwner) {
                binding.passengerNumberTextView.text = it.toString()
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
            })

            binding.selectVaccineSpinner.onItemSelectedListener = this
        }

        return  binding.root
    }

    private fun setSpinner(list: List<Objects>, spinner: Spinner) {
        val spinner: Spinner = spinner
        ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            list
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    override fun toastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}