package com.example.pmordo.presentation.ui.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.databinding.EditProfileFragmentBinding
import com.example.pmordo.presentation.models.User
import com.example.pmordo.presentation.models.UserHolder
import com.example.pmordo.presentation.models.UserHolder.address
import com.example.pmordo.presentation.models.UserHolder.birthday
import com.example.pmordo.presentation.models.UserHolder.selectedImageUri
import com.example.pmordo.presentation.models.UserHolder.userType
import com.example.pmordo.presentation.models.UserType

class EditProfileFragment : Fragment() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var binding: EditProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfileFragmentBinding.inflate(inflater, container, false)

        binding.loginUserName.setText(UserHolder.username)
        binding.loginEmail.setText(UserHolder.email)
        binding.loginPassword.setText(UserHolder.password)
        binding.phoneNumber.setText(UserHolder.phoneNumber)
        binding.inputWhatsApp.setText(UserHolder.whatsApp)
        binding.inputTelegram.setText(UserHolder.telegram)
        binding.companyName.setText(UserHolder.work)
        binding.companyPosition.setText(UserHolder.specialization)

        binding.saveButton.setOnClickListener {
            val newUsername = binding.loginUserName.text.toString()
            val newEmail = binding.loginEmail.text.toString()
            val newPassword = binding.loginPassword.text.toString()
            val newPhoneNumber = binding.phoneNumber.text.toString()
            val newWork = binding.companyName.text.toString()
            val newSpecialization = binding.companyPosition.text.toString()
            val newWhatsApp = binding.inputWhatsApp.text.toString()
            val newTelegram = binding.inputTelegram.text.toString()



            UserHolder.username = newUsername
            UserHolder.email = newEmail
            UserHolder.password = newPassword
            UserHolder.phoneNumber = newPhoneNumber
            UserHolder.work = newWork
            UserHolder.specialization = newSpecialization
            UserHolder.whatsApp = newWhatsApp
            UserHolder.telegram = newTelegram
            UserHolder.work = newWork

            val updatedUser = User(
                id = id.toLong(),
                email = newEmail,
                username = newUsername,
                password = newPassword,
                phoneNumber = newPhoneNumber,
                job = newWork,
                specialization = newSpecialization,
                userWhatsapp = newWhatsApp,
                userTelegram = newTelegram,
                address = address,
                birthday = birthday,
                userType = UserType.unknown,
                userPhoto = selectedImageUri
            )


            findNavController().navigateUp()
        }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.userPhoto.setOnClickListener {
            openGallery()
        }
        return binding.root
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data


            binding.userPhoto.setImageURI(selectedImageUri)

            UserHolder.selectedImageUri = selectedImageUri

            val profileFragment =
                parentFragmentManager.findFragmentByTag("ProfileFragment") as? ProfileFragment
            profileFragment?.updateProfilePhoto(selectedImageUri)
        }
    }

}