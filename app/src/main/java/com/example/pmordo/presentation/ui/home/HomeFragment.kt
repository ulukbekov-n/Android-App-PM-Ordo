package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.View
import com.example.pmordo.databinding.FragmentHomeBinding
import com.example.pmordo.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigationView()
    }
}