package com.example.pmordo.data.repositoryImple

import com.example.pmordo.data.networks.MainApi
import com.example.pmordo.domain.models.LoginRequest
import com.example.pmordo.domain.repository.UserRepository


class UserRepositoryImple(private val api: MainApi) : UserRepository {
    override suspend fun loginUser(userLoginRequest: LoginRequest) {
        api.auth(loginRequest = userLoginRequest)
    }
}