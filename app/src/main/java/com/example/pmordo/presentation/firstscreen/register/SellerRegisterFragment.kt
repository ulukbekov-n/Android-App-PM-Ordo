package com.example.pmordo.presentation.firstscreen.register

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.R
import com.example.pmordo.databinding.SellerRegisterFragmentBinding

class SellerRegisterFragment: Fragment() {
    private lateinit var binding:SellerRegisterFragmentBinding
    private var isPasswordVisible = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SellerRegisterFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val passwordEditText = binding.inputPassword
        val passwordVisibilityToggle = binding.passwordVisibilityToggle

        passwordVisibilityToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                passwordEditText.transformationMethod = null
                passwordVisibilityToggle.setImageResource(R.drawable.show_pass)
            } else {
                passwordEditText.transformationMethod = PasswordTransformationMethod()
                passwordVisibilityToggle.setImageResource(R.drawable.hide_pass)
            }

            passwordEditText.text?.let { it1 -> passwordEditText.setSelection(it1.length) }
        }
    }
}