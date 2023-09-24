package com.example.pmordo.data.data.service

import com.example.pmordo.data.data.models.SellerSignUpData
import com.example.pmordo.data.data.models.SignUpResponseDataModel
import com.example.pmordo.data.data.models.UserSignUpData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("accounts/users/")
    suspend fun signUp(@Body request: UserSignUpData): Response<SignUpResponseDataModel>
    @POST("accounts/sellers/")
    suspend fun signUpSeller(@Body request: SellerSignUpData): Response<SignUpResponseDataModel>
//    @POST("auth/token")
//    fun login(@Body credentials: LoginRequest): Call<TokenResponse>
//
//    @POST("auth/token/refresh")
//    fun refreshToken(@Body refreshToken: RefreshTokenRequest): Call<TokenResponse>

//    @POST("auth/token")
//    suspend fun auth(@Body loginRequest: LoginRequest): User


}