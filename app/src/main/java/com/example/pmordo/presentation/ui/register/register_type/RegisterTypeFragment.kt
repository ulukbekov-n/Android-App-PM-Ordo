package com.example.pmordo.presentation.ui.register.register_type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pmordo.databinding.RegisterTypeFragmentBinding
import com.example.pmordo.presentation.models.UserType

class RegisterTypeFragment : Fragment() {

    private val binding by lazy {
        RegisterTypeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buyer.setOnClickListener {
            findNavController().navigate(
                RegisterTypeFragmentDirections.actionRegisterTypeFragmentToBuyerRegisterFragment(
                    UserType.buyer
                )
            )
        }
        binding.seller.setOnClickListener {
            findNavController().navigate(
                RegisterTypeFragmentDirections.actionRegisterTypeFragmentToSalesmanRegisterFragment(
                    UserType.salesman
                )
            )
        }
    }
}