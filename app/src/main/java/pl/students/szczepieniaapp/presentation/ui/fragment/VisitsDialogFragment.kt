package pl.students.szczepieniaapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.VisitsDialogFragmentBinding

@AndroidEntryPoint
class VisitsDialogFragment() : DialogFragment() {

    private var _binding: VisitsDialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VisitsDialogFragmentBinding.inflate(inflater, container, false)
        //if (data.isNotEmpty()) { binding.noVisitTextView.visibility = View.GONE }
        binding.visitsListView!!.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayOf("1", "2", "3", "4"))

        return binding.root
    }

}