package pl.students.szczepieniaapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import pl.students.szczepieniaapp.R
import pl.students.szczepieniaapp.presentation.ui.viewmodel.ShareDataViewModel

abstract class MyFragment<Binding> : Fragment() {

    protected var _binding: Binding? = null
    protected val binding get() = _binding!!

//    protected lateinit var dialogBuilder: AlertDialog.Builder
//    protected lateinit var dialog: AlertDialog

    protected val shareDataViewModel: ShareDataViewModel by activityViewModels()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    override fun toastMessage(view: View, message: String) {
//        Toast.makeText(view.context, message, Toast.LENGTH_LONG).show()
//    }
//
//    override fun setDialog(view: View, string: String) {
//        dialogBuilder = AlertDialog.Builder(view.context)
//        val newView = LayoutInflater.from(view.context).inflate(R.layout.register_dialog, null)
//        val textView = newView.findViewById<TextView>(R.id.description)
//        textView.text = string
//        dialogBuilder.setView(newView)
//        dialog = dialogBuilder.create()
//        dialog.show()
//    }
//
//    override fun dismissDialog() {
//        dialog.dismiss()
//    }
}