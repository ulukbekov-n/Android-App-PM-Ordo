package com.example.pmordo.presentation.firstscreen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.SellerRegisterFragmentBinding

class SellerRegisterFragment: Fragment() {
    private lateinit var binding:SellerRegisterFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SellerRegisterFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}