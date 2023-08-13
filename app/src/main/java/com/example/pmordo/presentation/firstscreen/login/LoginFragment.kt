package com.example.pmordo.presentation.firstscreen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.pmordo.databinding.FragmentLoginBinding
import com.example.pmordo.domain.models.LoginRequest

class LoginFragment : Fragment() {
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModel()

    private fun goToHomeScreen() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerClick()
    }

    private fun registerClick() {
        binding.loginButton.setOnClickListener {
            startLoginUser()
        }
    }

    private fun startLoginUser() = with(binding) {
        val loginUser = LoginRequest(
            email = loginEmail.toString(),
            password = loginPassword.toString()
        )
        viewModel.loginUser(loginUser = loginUser)
        goToHomeScreen()
    }
}