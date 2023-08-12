package com.example.pmordo.presentation.firstscreen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.CreatePasswordFragmentBinding

class CreatePasswordFragment : Fragment() {
    lateinit var binding: CreatePasswordFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreatePasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}