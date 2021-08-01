package pl.students.szczepieniaapp.presentation.ui.fragment

import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.databinding.VaccinationQrCodeFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment
import pl.students.szczepieniaapp.presentation.ui.listener.VaccinationQRCodeListener
import pl.students.szczepieniaapp.presentation.ui.viewmodel.VaccinationQRCodeViewModel

@AndroidEntryPoint
class VaccinationQRCodeFragment : MyFragment<VaccinationQrCodeFragmentBinding>(),
    VaccinationQRCodeListener {

    private val viewModel : VaccinationQRCodeViewModel by viewModels()

    private lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VaccinationQrCodeFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel

        viewModel.apply {

            QRCodeDialogLoading.observe(viewLifecycleOwner){
                if (it) {
                    binding.qeCodeProgressBar.visibility = View.VISIBLE
                    binding.qrCodeButton.visibility = View.GONE
                } else {
                    binding.qeCodeProgressBar.visibility = View.GONE
                    binding.qrCodeButton.visibility = View.VISIBLE
                }
            }

            displayDialog.observe(viewLifecycleOwner) {
                if (it != null) setProgressDialog(requireView(), displayDialog.value!!)
            }
        }

        return binding.root
    }

    override fun setProgressDialog(view: View, qrBits: Bitmap) {
        dialogBuilder = AlertDialog.Builder(view.context)
        val newView = LayoutInflater.from(view.context).inflate(R.layout.qr_code_dialog, null)
        val qrPlaceHolder = newView.findViewById<ImageView>(R.id.qrPlaceHolder)
        qrPlaceHolder.setImageBitmap(qrBits)
        dialogBuilder.setView(newView)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setPositiveButton(R.string.vaccination_qr_code_fragment_cancel_text) { dialog, _ ->
            dialog.dismiss()
        }
        dialog = dialogBuilder.create()
        dialog.show()
    }

    override fun dismissProgressDialog() {
        dialog.dismiss()
    }
}