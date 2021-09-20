package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.VisitsDialogFragmentBinding
import pl.students.szczepieniaapp.presentation.ui.viewmodel.ShareDataViewModel

@AndroidEntryPoint
class VisitsDialogFragment(data: Array<String>) : DialogFragment() {

    private var _binding: VisitsDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private val shareDataViewModel: ShareDataViewModel by activityViewModels()

    private val data: Array<String> = data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VisitsDialogFragmentBinding.inflate(inflater, container, false)
        if (data.isNotEmpty()) {
            binding.visitsListView!!.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data)
            binding.visitsListView.visibility = View.VISIBLE
        }
        else binding.noVisitTextView.visibility = View.VISIBLE

        binding.visitsListView.setOnItemClickListener { parent, view, position, id ->
            Log.d(VisitsDialogFragment::class.java.name, "selected: ${parent.adapter.getItem(position)}")
            shareDataViewModel._visitTime.postValue(parent.adapter.getItem(position) as String)
            dismiss()
        }

        return binding.root
    }

}