package com.example.pmordo.domain.repository

import com.example.pmordo.domain.models.LoginRequest
import com.example.pmordo.domain.usecase.UserLoginUseCase

interface UserRepository {
    suspend fun loginUser(userLoginRequest: LoginRequest)
}