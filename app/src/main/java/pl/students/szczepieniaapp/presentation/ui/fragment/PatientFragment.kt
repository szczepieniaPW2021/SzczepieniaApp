package pl.students.szczepieniaapp.presentation.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AlertDialog
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.PatientFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.listener.PatientListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.PatientViewModel

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

    override fun setProgressDialog(view: View, qrBits: Bitmap) {
        dialogBuilder = AlertDialog.Builder(view.context)
        val newView = LayoutInflater.from(view.context).inflate(R.layout.qr_code_dialog, null)
        val qrPlaceHolder = newView.findViewById<ImageView>(R.id.qrPlaceHolder)
        qrPlaceHolder.setImageBitmap(qrBits)
        dialogBuilder.setView(newView)
        dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun dismissProgressDialog() {
        dialog.dismiss()
    }
}