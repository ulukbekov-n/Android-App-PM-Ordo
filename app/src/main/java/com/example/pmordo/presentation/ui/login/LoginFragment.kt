package com.example.pmordo.presentation.ui.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.IntentSender
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.FragmentLoginBinding
import com.example.pmordo.presentation.base.BaseFragment
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    //        BaseFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private lateinit var textWatcher: TextWatcher
    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest
    private val REQ_ONE_TAP = "ONE_TAP_REQUEST_KEY"
    private var showOneTapUI = true
//    override val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val googleButton = binding.googleLogin
        registerClick()
        oneTapClient = Identity.getSignInClient(requireContext())
        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.web_client_id))
                    // Show all accounts on the device.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
        val oneTapResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle successful sign-in
                val data = result.data
                if (data != null) {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    if (idToken != null) {
                        // You have the ID token, you can use it for further authentication
                        val email = credential.getId()
                        Toast.makeText(
                            requireContext(),
                            "Sign-in successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d(TAG, "Got ID token: $idToken")
                        Log.d(TAG, "User Email: $email")
                        findNavController().navigate(R.id.main_navigation)
                    } else {
                        // Handle the case where ID token is null
                        Log.e(TAG, "ID token is null")
                    }
                } else {
                    // Handle the case where data is null
                    Log.e(TAG, "Data is null")
                }
            } else {
                // Handle sign-in failure or cancellation
                Log.e(TAG, "Sign-in failed or canceled")
            }
        }
        googleButton.setOnClickListener {
            oneTapClient.beginSignIn(signUpRequest)
                .addOnSuccessListener(requireActivity()) { result ->
                    try {
                        val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender)

                            .build()
                        oneTapResultLauncher.launch(intentSenderRequest)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener(requireActivity()) { e ->
                    // Handle failure
                    Log.d(TAG, e.localizedMessage)
                }
        }
    }

    private fun registerClick() = with(binding) {
//        binding.loginButton.setOnClickListener {
//            startLoginUser()
//        }
        signUpButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterTypeFragment())
        }
    }

//    private fun startLoginUser() = with(binding) {
//        val loginUser = LoginRequest(
//            email = loginEmail.toString(),
//            password = loginPassword.toString()
//        )
//        viewModel.loginUser(loginUser = loginUser)
//
//    }
}