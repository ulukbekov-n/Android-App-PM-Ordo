package com.example.pmordo.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pmordo.data.data.repository.ProductRepository
import com.example.pmordo.presentation.base.BaseViewModel
import com.example.pmordo.presentation.models.Product
import kotlinx.coroutines.launch

class HomeViewModel() : BaseViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products


}
