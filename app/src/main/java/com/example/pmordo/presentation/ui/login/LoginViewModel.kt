package com.example.pmordo.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmordo.domain.usecase.UserLoginUseCase
import com.example.pmordo.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: UserLoginUseCase) : BaseViewModel() {

//    fun loginUser(loginUser: LoginRequest) = viewModelScope.launch {
//        loginUserUseCase.invoke(loginUser)
//    }

}