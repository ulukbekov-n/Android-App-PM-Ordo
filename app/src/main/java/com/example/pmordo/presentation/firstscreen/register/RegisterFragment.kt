package com.example.pmordo.presentation.firstscreen.register
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.IntentSender
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.viewModels.RegisterViewModel
import com.example.pmordo.api.UserHolder
import com.example.pmordo.databinding.RegisterFragmentBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient


class   RegisterFragment : Fragment() {
    lateinit var binding: RegisterFragmentBinding
    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var button: Button

    private lateinit var textWatcher: TextWatcher
    private lateinit var viewModel: RegisterViewModel
    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest
    private val REQ_ONE_TAP = "ONE_TAP_REQUEST_KEY"
    private var showOneTapUI = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        editTextUserName = binding.loginUserName
        editTextEmail = binding.loginEmail
        button = binding.registerButton
        val googleButton = binding.googleLogin
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val userName = editTextUserName.text.toString().trim()
                val email = editTextEmail.text.toString().trim()
                viewModel.onUsernameTextChanged(userName)
                viewModel.onEmailTextChanged(email)
            }
        }
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

        // Create a result launcher for handling the One Tap sign-in result
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

                        // Navigate to the profile Main fragment here
                        findNavController().navigate(R.id.action_registerFragment_to_profileMainFragment)
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
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        viewModel.isButtonEnabled.observe(viewLifecycleOwner, Observer { isEnabled ->
            button.isEnabled = isEnabled
            if (isEnabled) {
                button.setBackgroundResource(R.drawable.enabled_back)
            } else {
                button.setBackgroundResource(R.drawable.back)
            }
        })

        binding.registerButton.setOnClickListener {
            val userName = editTextUserName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()

            if (userName.isEmpty() || email.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putString("userName", userName)
                    putString("email", email)
                }
                val createPasswordFragment = CreatePasswordFragment()
                createPasswordFragment.arguments = bundle
                UserHolder.username = userName
                UserHolder.email = email

                findNavController().navigate(
                    R.id.action_registerFragment_to_createPasswordFragment,
                    bundle
                )
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        editTextUserName.addTextChangedListener(textWatcher)
        editTextEmail.addTextChangedListener(textWatcher)
        return binding.root
    }
}
