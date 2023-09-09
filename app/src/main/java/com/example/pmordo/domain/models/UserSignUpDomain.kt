package com.example.pmordo.domain.models

data class UserSignUpDomain(
    val email: String,
    val username: String,
    val password: String,
    val phoneNumber: String,
    val userPhoto: String,
    val address: String,
    val birthday: String,
    val job: String,
    val specialization: String,
    val userWhatsapp: String,
    val userTelegram: String,
    val userType: String,
)