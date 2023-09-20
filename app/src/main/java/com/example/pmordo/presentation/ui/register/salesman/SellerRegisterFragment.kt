package com.example.pmordo.presentation.ui.register.seller

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.SellerRegisterFragmentBinding
import com.example.pmordo.presentation.base.BaseFragment
import com.example.pmordo.presentation.models.SellerSignUp
import com.example.pmordo.presentation.models.UserType
import com.example.pmordo.presentation.ui.progress_dialog.ProgressDialog
import com.example.pmordo.presentation.ui.register.buyer.BuyerRegisterFragmentArgs
import com.example.pmordo.presentation.utils.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SellerRegisterFragment :
    BaseFragment<SellerRegisterFragmentBinding, SellerRegisterViewModel>(SellerRegisterFragmentBinding::inflate) {

    override val viewModel: SellerRegisterViewModel by viewModel()

    private val userType: UserType by lazy(LazyThreadSafetyMode.NONE) {
        SellerRegisterFragmentArgs.fromBundle(requireArguments()).rol

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
            !loginName.validateName() -> showSnackbar(
                message = getString(R.string.login_input_format_error),
            )

            !inputEmail.validateEmail() -> showSnackbar(
                message = getString(R.string.email_input_format_error),
            )

            !inputPassword.validatePassword() -> showSnackbar(
                message = getString(R.string.password_input_format_error),
            )

            !loginINN.validateINN() -> showSnackbar(
                message = getString(R.string.inn_input_format_error),
            )

            !companyName.validateName() -> showSnackbar(
                message = getString(R.string.login_input_format_error),
            )

            !companyPosition.validateName() -> showSnackbar(
                message = getString(R.string.login_input_format_error),
            )

            else -> {
                startSignUp()
                showSnackbar("You have successfully registered as a seller")
            }
        }
    }

    private fun startSignUp() = with(binding()) {
        val newSeller = SellerSignUp(
            email = inputEmail.text.toString().trim(),
            username = loginName.text.toString().trim(),
            password = inputPassword.text.toString().trim(),
            userType = userType.name,
            inn = loginINN.text.toString().trim(),
            companyName = companyName.text.toString().trim(),
            companyPosition = companyPosition.text.toString().trim(),
        )
        viewModel.startSignUp(newSeller)
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
                // Handle navigation for buyer
            }

            UserType.salesman -> {
                findNavController().navigate(
                    R.id.main_navigation,
                    bundleOf(),
                    createNavOptionsWithAnimations()
                )
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
