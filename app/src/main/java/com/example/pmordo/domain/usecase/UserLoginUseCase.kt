package com.example.pmordo.domain.usecase

import com.example.pmordo.domain.repository.LoginRepository

class UserLoginUseCase(private val repository: LoginRepository) {
//    suspend operator fun invoke(userLoginRequest:LoginRequest)=
//        repository.loginUser(userLoginRequest = userLoginRequest)
}