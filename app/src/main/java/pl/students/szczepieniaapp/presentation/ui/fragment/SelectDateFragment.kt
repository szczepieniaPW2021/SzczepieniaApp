package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.SelectDateDialogBinding
import pl.students.szczepieniaapp.presentation.ui.viewmodel.SelectDateViewModel
import pl.students.szczepieniaapp.presentation.ui.viewmodel.ShareDataViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SelectDateFragment : DialogFragment() {

    private var _binding: SelectDateDialogBinding? = null
    private val binding get() = _binding!!

    private val  viewModel : SelectDateViewModel by viewModels()
    private val shareDataViewModel: ShareDataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SelectDateDialogBinding.inflate(inflater, container, false)

        binding.calendarView.minDate = viewModel.getCurrentTime()

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            Log.d(SelectDateViewModel::class.java.simpleName, "setCalendarView: ${year}/${month}/${dayOfMonth}")
            var calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            shareDataViewModel._visitDate.postValue(SimpleDateFormat("dd-MM-yyyy").format(calendar.time))
            dismiss()
        }

        return binding.root
    }

}