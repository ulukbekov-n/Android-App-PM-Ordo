package com.example.pmordo.data.data.models

import com.google.gson.annotations.SerializedName

data class UserSignUpData (
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("photo") val userPhoto: String,
    @SerializedName("address") val address: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("job") val job: String,
    @SerializedName("specialization") val specialization: String,
    @SerializedName("whatsapp") val userWhatsapp: String,
    @SerializedName("telegram") val userTelegram: String,
    @SerializedName("role") val userType: String,
)