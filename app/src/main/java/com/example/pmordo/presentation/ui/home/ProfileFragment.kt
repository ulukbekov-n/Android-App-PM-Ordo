package com.example.pmordo.presentation.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.ProfileFragmentBinding
import com.example.pmordo.domain.models.UserDomain
import com.example.pmordo.presentation.models.User
import com.example.pmordo.presentation.models.UserHolder
import com.example.pmordo.presentation.models.UserSignUp

class ProfileFragment: Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProfileFragmentBinding.inflate(inflater,container,false)
        binding.profileName.text =UserHolder.username
        binding.profilePhoto.setImageURI(UserHolder.selectedImageUri)
        binding.profileEmail.text = UserHolder.email
        binding.profilePhone.text = UserHolder.phoneNumber
        binding.changeProfileButton.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)}

        return binding.root
    }
    fun updateProfilePhoto(selectedImageUri: Uri?) {

        binding.profilePhoto.setImageURI(selectedImageUri)
    }
}