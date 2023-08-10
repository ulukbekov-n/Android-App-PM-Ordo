package com.example.pmordo

import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("auth/token")
    suspend fun auth(@Body loginRequest: LoginRequest):User
}