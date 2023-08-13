package com.example.pmordo.data.networks

import com.example.pmordo.api.LoginResponse
import com.example.pmordo.api.RegisterRequest
import com.example.pmordo.api.TokenObtainPairRequest
import com.example.pmordo.domain.models.LoginRequest
import com.example.pmordo.domain.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("accounts/users/")
    fun register(@Body request: RegisterRequest): Call<Unit>
    @POST("auth/token")
    suspend fun auth(@Body loginRequest: LoginRequest): User

}