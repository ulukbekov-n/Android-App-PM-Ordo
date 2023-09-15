package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.EditProfileFragmentBinding
import com.example.pmordo.databinding.ProfileFragmentBinding

class EditProfileFragment:Fragment() {
    private lateinit var binding: EditProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = EditProfileFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}