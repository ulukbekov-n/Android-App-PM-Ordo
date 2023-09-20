package com.example.pmordo.presentation.ui.screen_splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.pmordo.R
import com.example.pmordo.databinding.FragmentSplashBinding
import com.example.pmordo.presentation.base.BaseFragment
import com.example.pmordo.presentation.ui.activity_main.MainActivity
import com.example.pmordo.presentation.utils.extension.launchOnLifecycle
import com.example.pmordo.presentation.utils.extension.launchWhenViewStarted
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {

    override val viewModel: SplashViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigationView()
        (requireActivity() as MainActivity).setDrawerVisibility(false)
        observeData()
    }

    private fun observeData() = with(viewModel) {
        launchOnLifecycle { isProgressBarVisibleFlow.observe(::setProgressBarVisible) }
        launchWhenViewStarted {
            navigateToFlow.observe {
                navigateTo(it)
                Log.i("Umar", "navigateToFlow $it")

            }
        }
    }

    private fun navigateTo(destination: StartNavigationDestination) {
        navControllerPopBackStackInclusive()
        when (destination) {
            StartNavigationDestination.NavigateToLoginScreen -> navigateToLoginScreens()
            StartNavigationDestination.NavigateToMainScreen -> navigateToMainScreens()
            StartNavigationDestination.NavigateToAccountHasDeletedScreen -> navigateToLoginScreens()
            StartNavigationDestination.NavigateToSalesmanScreen -> navigateToMainScreens()
        }
        Log.i("Umar", "navigateTo $destination")

    }

    private fun navigateToLoginScreens() = findNavController().navigate(
        R.id.login_navigation,
        bundleOf(),
        createNavOptionsWithAnimations()
    )

    private fun navigateToMainScreens() = findNavController().navigate(
        R.id.main_navigation,
        bundleOf(),
        createNavOptionsWithAnimations()
    )

    private fun setProgressBarVisible(isVisible: Boolean) {
        binding().progressBar.isVisible = isVisible
    }

    private fun navControllerPopBackStackInclusive() =
        findNavController().popBackStack(R.id.splash_navigation, false)

}