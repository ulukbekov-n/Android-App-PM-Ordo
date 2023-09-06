package com.example.pmordo.presentation.firstscreen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.GreetingFragmentBinding

class GreetingFragment:Fragment() {
    private lateinit var binding:GreetingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GreetingFragmentBinding.inflate(inflater,container,false)
        binding.buyer.setOnClickListener{
            findNavController().navigate(R.id.action_greetingFragment_to_registerFragment)
        }
        binding.seller.setOnClickListener{
            findNavController().navigate(R.id.action_greetingFragment_to_sellerRegisterFragment)
        }
        return binding.root
    }
}