package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProfileFragmentBinding.inflate(inflater,container,false)
        binding.changeProfileButton.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)}
        return binding.root
    }
}