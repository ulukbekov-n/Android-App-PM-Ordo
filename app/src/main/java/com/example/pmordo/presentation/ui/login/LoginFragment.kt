package com.example.pmordo.presentation.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.pmordo.databinding.FragmentLoginBinding
import com.example.pmordo.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment :
    BaseFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerClick()

    }

    private fun registerClick() = with(binding()) {
//        binding.loginButton.setOnClickListener {
//            startLoginUser()
//        }
        signUpButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterTypeFragment())
        }
    }

//    private fun startLoginUser() = with(binding) {
//        val loginUser = LoginRequest(
//            email = loginEmail.toString(),
//            password = loginPassword.toString()
//        )
//        viewModel.loginUser(loginUser = loginUser)
//
//    }
}