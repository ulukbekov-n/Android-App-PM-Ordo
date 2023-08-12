package com.example.pmordo.data.networks

import com.example.pmordo.domain.models.LoginRequest
import com.example.pmordo.domain.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("auth/token")
    suspend fun auth(@Body loginRequest: LoginRequest): User
}