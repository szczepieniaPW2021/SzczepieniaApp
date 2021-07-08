package pl.students.szczepieniaapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.SearchPatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.listener.SearchPatientListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.SearchPatientViewModel
import java.util.*

@AndroidEntryPoint
class SearchPatientFragment : MyFragment<SearchPatientFragmentBinding>(), SearchPatientListener {

    private val viewModel : SearchPatientViewModel by viewModels()

    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchPatientFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        viewModel.apply {
            binding.searchView.setOnQueryTextListener(this)
            persons.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    binding.patientNameTextView.text = getPatientName()
                    binding.patientPeselTextView.text = getPatientPesel()
                    binding.registerVaccinationBtn.visibility = View.VISIBLE
                    binding.patientDataLinearLayout.visibility = View.VISIBLE
                    binding.registerVaccinationLinearLayout.visibility = View.VISIBLE
                    binding.noPatientLinearLayout.visibility = View.GONE
                } else {
                    binding.patientDataLinearLayout.visibility = View.GONE
                    binding.registerVaccinationBtn.visibility = View.GONE
                    binding.registerVaccinationLinearLayout.visibility = View.GONE
                    binding.noPatientLinearLayout.visibility = View.VISIBLE
                }
            }
            vaccineDoses.observe(viewLifecycleOwner){
                setSpinner(it as List<Objects>, binding.selectVaccineDoseSpinner)
            }
            vaccineTypes.observe(viewLifecycleOwner){
                setSpinner(it as List<Objects>, binding.selectVaccineTypeSpinner)
            }

            binding.selectVaccineDoseSpinner.onItemSelectedListener = this
            binding.selectVaccineTypeSpinner.onItemSelectedListener = this
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.selectContext(context)
    }

    override fun toastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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
}