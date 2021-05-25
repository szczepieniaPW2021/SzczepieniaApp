package pl.students.szczepieniaapp.presentation.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.PatientCalendarFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.listener.PatientCalendarListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.PatientCalendarViewModel
import java.util.*

@AndroidEntryPoint
class PatientCalendarFragment : MyFragment<PatientCalendarFragmentBinding>(), PatientCalendarListener {

    private val  viewModel : PatientCalendarViewModel by viewModels()

    private lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog
    private lateinit var childFM: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PatientCalendarFragmentBinding.inflate(inflater, container, false)
        childFM = childFragmentManager
        binding.viewmodel = viewModel
        viewModel.apply {
            cities.observe(viewLifecycleOwner){
                setSpinner(it as List<Objects>, binding.selectCitySpinner)
            }
            facilities.observe(viewLifecycleOwner){
                setSpinner(it as List<Objects>, binding.selectFacilitySpinner)
            }

            binding.selectCitySpinner.onItemSelectedListener = this
            binding.selectFacilitySpinner.onItemSelectedListener = this

            isFacilitySpinnerVisible.observe(viewLifecycleOwner){
                if (it) {
                    binding.selectFacilityRelativeLayout.visibility = View.VISIBLE
                    binding.selectFacilityTextView.visibility = View.VISIBLE
                } else {
                    binding.selectFacilityTextView.visibility = View.INVISIBLE
                    binding.selectFacilityRelativeLayout.visibility = View.INVISIBLE
                }
            }

            isCalendarVisible.observe(viewLifecycleOwner){
                binding.calendarView.minDate = viewModel.getCurrentTime()
                binding.calendarView.maxDate = viewModel.setMaxDate()
                if (it) binding.calendarView.visibility = View.VISIBLE else binding.calendarView.visibility = View.INVISIBLE
            }

            viewModel.getDate(binding.calendarView)
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var dialog = VisitsDialogFragment()
            dialog.show(childFM, "VisitsDialogFragment")
        }

        return binding.root
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
}