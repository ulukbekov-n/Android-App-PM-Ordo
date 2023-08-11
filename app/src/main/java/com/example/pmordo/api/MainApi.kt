package com.example.pmordo.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("accounts/users/")
    fun register(@Body request: RegisterRequest): Call<Unit>
}