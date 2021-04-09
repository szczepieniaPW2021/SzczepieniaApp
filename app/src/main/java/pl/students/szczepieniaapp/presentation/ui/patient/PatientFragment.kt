package pl.students.szczepieniaapp.presentation.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AlertDialog
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.PatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment

@AndroidEntryPoint
class PatientFragment : MyFragment<PatientFragmentBinding>(), PatientListener {

    private val viewModel : PatientViewModel by viewModels()

    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PatientFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun setProgressDialog(view: View) {
        dialogBuilder = AlertDialog.Builder(view.context)
        dialogBuilder.setView(LayoutInflater.from(view.context).inflate(R.layout.qr_code_dialog, null))
        dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun dismissProgressDialog() {
        dialog.dismiss()
    }
}