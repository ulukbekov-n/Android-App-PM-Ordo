package com.example.pmordo.domain.usecase

import com.example.pmordo.domain.models.LoginRequest
import com.example.pmordo.domain.repository.UserRepository

class UserLoginUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userLoginRequest:LoginRequest)=
        repository.loginUser(userLoginRequest = userLoginRequest)
}