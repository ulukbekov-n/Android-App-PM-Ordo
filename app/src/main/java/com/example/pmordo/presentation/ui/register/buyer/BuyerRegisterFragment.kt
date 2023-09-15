package com.example.pmordo.presentation.ui.register.buyer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.FragmentBuyerRegisterBinding
import com.example.pmordo.presentation.base.BaseFragment
import com.example.pmordo.presentation.models.UserSignUp
import com.example.pmordo.presentation.models.UserType
import com.example.pmordo.presentation.ui.progress_dialog.ProgressDialog
import com.example.pmordo.presentation.utils.extension.hideKeyboard
import com.example.pmordo.presentation.utils.extension.launchOnLifecycle
import com.example.pmordo.presentation.utils.extension.launchWhenViewStarted
import com.example.pmordo.presentation.utils.extension.showOnlyOne
import com.example.pmordo.presentation.utils.extension.validateEmail
import com.example.pmordo.presentation.utils.extension.validateName
import com.example.pmordo.presentation.utils.extension.validatePassword
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class BuyerRegisterFragment :
    BaseFragment<FragmentBuyerRegisterBinding, BuyerRegisterViewModel>(FragmentBuyerRegisterBinding::inflate) {

    override val viewModel: BuyerRegisterViewModel by viewModel()

    private val userType: UserType by lazy(LazyThreadSafetyMode.NONE) {
        BuyerRegisterFragmentArgs.fromBundle(requireArguments()).rol
    }

    private val progressDialog: ProgressDialog by lazy(LazyThreadSafetyMode.NONE) {
        ProgressDialog.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        hideBottomNavigationView()
        observeData()
    }

    private fun setOnClickListeners() = with(binding()) {
        registerButton.setOnClickListener {
            requireActivity().hideKeyboard(requireView())
            validateUserValuesAndStartSignUp()
        }
    }

    private fun validateUserValuesAndStartSignUp() = with(binding()) {
        when {
            !loginUserName.validateName() -> showSnackbar(
                message = getString(R.string.login_input_format_error),
            )

            !loginEmail.validateEmail() -> showSnackbar(
                message = getString(R.string.email_input_format_error),
            )

            !passwordInput.validatePassword() -> showSnackbar(
                message = getString(R.string.password_input_format_error),
            )

            else -> {
                startSignUp()
                showSnackbar("You have successfully registered")
            }
        }
    }

    private fun startSignUp() = with(binding()) {
        val newUser = UserSignUp(
            email = loginEmail.text.toString().trim(),
            username = loginUserName.text.toString().trim(),
            password = passwordInput.text.toString().trim(),
            userType = userType.name,
        )
        viewModel.startSignUp(newUser)
    }

    private fun observeData() = with(viewModel) {
        launchOnLifecycle {
            isProgressBarVisibleFlow.observe(::setProgressBarVisibility)
        }
        launchWhenViewStarted {
            isErrorMessageVisibleFlow.observe(::setErrorMessageVisibility)
            isProgressDialogVisibleFlow.observe(::handleProgressDialogStatus)
            handleSignUpFlow.observe { handleSignUp(userType) }
        }
    }

    private fun handleSignUp(userType: UserType) {
        navControllerPopBackStackInclusive()
        when (userType) {
            UserType.buyer -> {
                findNavController().navigate(
                    R.id.main_navigation,
                    bundleOf(),
                    createNavOptionsWithAnimations()
                )
            }

            UserType.salesman -> {
                // Handle navigation for salesman
            }

            else -> {}
        }
    }

    private fun navControllerPopBackStackInclusive() =
        findNavController().popBackStack(R.id.login_navigation, false)

    private fun handleProgressDialogStatus(isShow: Boolean) {
        if (isShow) progressDialog.showOnlyOne(parentFragmentManager)
        else progressDialog.dismiss()
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        // Set progress bar visibility if needed
    }

    private fun setErrorMessageVisibility(isVisible: Boolean) {
        // Set error message visibility if needed
    }
}
