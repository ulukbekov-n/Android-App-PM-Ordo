package com.example.pmordo.data.data.models

import com.google.gson.annotations.SerializedName

data class UserSignUpData (
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,

)