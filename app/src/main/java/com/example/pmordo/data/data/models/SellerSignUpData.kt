package com.example.pmordo.data.data.models

import com.google.gson.annotations.SerializedName

data class SellerSignUpData (
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("inn") val inn: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("companyPosition") val companyPosition: String
)
