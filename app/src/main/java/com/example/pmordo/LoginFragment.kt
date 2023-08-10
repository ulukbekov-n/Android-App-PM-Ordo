package com.example.pmordo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.LoginFragmentBinding

class LoginFragment:Fragment() {
    lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= LoginFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}