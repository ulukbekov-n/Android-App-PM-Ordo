package com.example.pmordo.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.databinding.FragmentLoginBinding
import com.example.pmordo.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    //        BaseFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }
//    override val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerClick()
    }

    private fun registerClick() = with(binding) {
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