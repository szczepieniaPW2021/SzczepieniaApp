package pl.students.szczepieniaapp.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.SearchPatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.listener.SearchPatientListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.PatientViewModel
import pl.students.szczepieniaapp.presentation.ui.viewmodel.SearchPatientViewModel

@AndroidEntryPoint
class SearchPatientFragment : MyFragment<SearchPatientFragmentBinding>(), SearchPatientListener {

    private val viewModel : SearchPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchPatientFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        viewModel.apply {
            binding.searchView.setOnQueryTextListener(this)
            person.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    binding.patientNameTextView.text = getPatientName()
                    binding.patientPeselTextView.text = getPatientPesel()
                    binding.registerVaccinationBtn.visibility = View.VISIBLE
                    binding.patientDataLinearLayout.visibility = View.VISIBLE
                    binding.noPatientLinearLayout.visibility = View.GONE
                } else {
                    binding.patientDataLinearLayout.visibility = View.GONE
                    binding.registerVaccinationBtn.visibility = View.GONE
                    binding.noPatientLinearLayout.visibility = View.VISIBLE
                }
            }
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
}