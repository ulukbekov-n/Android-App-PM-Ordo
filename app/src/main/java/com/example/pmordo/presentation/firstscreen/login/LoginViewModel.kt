package com.example.pmordo.presentation.firstscreen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmordo.domain.models.LoginRequest
import com.example.pmordo.domain.usecase.UserLoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: UserLoginUseCase) : ViewModel() {

    fun loginUser(loginUser: LoginRequest)=viewModelScope.launch {
        loginUserUseCase.invoke(loginUser)
    }

}