package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.PatientCalendarFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.listener.PatientCalendarListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.PatientCalendarViewModel
import java.util.*

@AndroidEntryPoint
class PatientCalendarFragment : MyFragment<PatientCalendarFragmentBinding>(), PatientCalendarListener {

    private val  viewModel : PatientCalendarViewModel by viewModels()

    lateinit var dialogBuilder: AlertDialog.Builder
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
            selectedVisit.observe(viewLifecycleOwner){
                if (it != null) {
                    binding.patientPersonalDataLinearLayout.visibility = View.VISIBLE
                    binding.selectedTimeTextView.text = viewModel.getTimeAsString(requireContext())
                    binding.selectedDateTextView.text = viewModel.getDateAsString(requireContext())
                    binding.selectedLocationTextView.text = viewModel.getCityAndFacility(requireContext())
                    binding.signUpBtn.visibility = View.VISIBLE
                    binding.visitDataLinearLayout.visibility = View.VISIBLE
                    viewModel.scrollToBottom(binding.scrollView)
                } else {
                    binding.patientPersonalDataLinearLayout.visibility = View.GONE
                    binding.signUpBtn.visibility = View.GONE
                    binding.visitDataLinearLayout.visibility = View.GONE
                }
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
                if (it) {
                    binding.calendarView.visibility = View.VISIBLE
                    viewModel.scrollToBottom(binding.scrollView)
                } else {
                    binding.calendarView.visibility = View.INVISIBLE
                }
            }

            patientName.observe(viewLifecycleOwner) {
                binding.signUpBtn.isEnabled = arePatientDataProvided()
            }

            patientLastName.observe(viewLifecycleOwner) {
                binding.signUpBtn.isEnabled = arePatientDataProvided()
            }

            patientIdNumber.observe(viewLifecycleOwner) {
                binding.signUpBtn.isEnabled = arePatientDataProvided()
            }

        }

        viewModel.setCalendarView(binding.calendarView, childFM)

        shareDataViewModel.visitTime.observe(viewLifecycleOwner){
            viewModel._selectedVisit.postValue(it)
        }

        return binding.root
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

    override fun toastMessage(view: View, string: String) {
        Toast.makeText(view.context, string, Toast.LENGTH_LONG).show()
    }
}