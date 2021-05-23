package pl.students.szczepieniaapp.presentation.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.PatientCalendarFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.viewmodel.PatientCalendarViewModel

@AndroidEntryPoint
class PatientCalendarFragment : MyFragment<PatientCalendarFragmentBinding>() {

    private val  viewModel : PatientCalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PatientCalendarFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        setSelectCitySpinner()
        viewModel.apply {
            binding.selectCitySpinner.onItemSelectedListener = this
        }
        return binding.root
    }

    private fun setSelectCitySpinner() {
        val spinner: Spinner = binding.selectCitySpinner
        ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            viewModel.fetchCities()
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}