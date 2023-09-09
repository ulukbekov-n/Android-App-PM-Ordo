package com.example.pmordo.presentation.ui.register.salesman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.SalesmanRegisterFragmentBinding

class SalesmanRegisterFragment: Fragment() {

    private lateinit var binding:SalesmanRegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SalesmanRegisterFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}