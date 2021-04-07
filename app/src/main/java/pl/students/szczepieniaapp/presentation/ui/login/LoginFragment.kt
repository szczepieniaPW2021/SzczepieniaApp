package pl.students.szczepieniaapp.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.students.szczepieniaapp.databinding.LoginFragmentBinding
import pl.students.szczepieniaapp.presentation.MyFragment

@AndroidEntryPoint
class LoginFragment : MyFragment<LoginFragmentBinding>() {

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }
}