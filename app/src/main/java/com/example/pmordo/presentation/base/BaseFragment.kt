package com.example.pmordo.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.example.pmordo.R
import com.example.pmordo.presentation.utils.extension.hide
import com.example.pmordo.presentation.utils.extension.launchWhenViewStarted
import com.example.pmordo.presentation.utils.extension.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel>(
    private val binder: (LayoutInflater, ViewGroup?, Boolean) -> V,
) : Fragment() {

    protected abstract val viewModel: VM

    private var viewBinding: V? = null

    protected fun binding(): V = checkNotNull(viewBinding)

    private val bottomNavigationView: BottomNavigationView? by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view) ?: null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = binder.invoke(inflater, container, false)
        this.viewBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRecourse()
    }


    fun showBottomNavigationView() = bottomNavigationView?.apply { show() }

    fun hideBottomNavigationView() = bottomNavigationView?.apply { hide() }


    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            isErrorMessageIdFlow.observe { showSnackbar(it.format(requireContext())) }
        }
    }

    fun showSnackbar(message: String) {
        Snackbar.make(
            requireView(),
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

     fun createNavOptionsWithAnimations() = NavOptions
        .Builder()
        .setEnterAnim(R.anim.slide_up)
        .setExitAnim(R.anim.slide_down)
        .setPopEnterAnim(R.anim.slide_up)
        .setPopExitAnim(R.anim.slide_down)
        .build()


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }


}