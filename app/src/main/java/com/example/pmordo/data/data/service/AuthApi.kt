package com.example.pmordo.data.data.service

import com.example.pmordo.data.data.models.SellerSignUpData
import com.example.pmordo.data.data.models.SignUpResponseDataModel
import com.example.pmordo.data.data.models.UserSignUpData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("accounts/users/")
    suspend fun signUp(@Body request: UserSignUpData): Response<SignUpResponseDataModel>
    @POST("accounts/sellers/") // Replace with the actual endpoint
    suspend fun signUpSeller(@Body request: SellerSignUpData): Response<SignUpResponseDataModel>


//    @POST("auth/token")
//    suspend fun auth(@Body loginRequest: LoginRequest): User


}