package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.FragmentCartBinding

class CartFragment: Fragment() {
    private lateinit var binding:FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}