package com.example.pmordo.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pmordo.databinding.FragmentHomeBinding
import com.example.pmordo.databinding.RegisterTypeFragmentBinding
import com.example.pmordo.presentation.base.BaseFragment

class HomeFragment() : Fragment(){
//    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {


    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        showBottomNavigationView()
    }
}