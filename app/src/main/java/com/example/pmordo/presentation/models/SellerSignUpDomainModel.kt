package com.example.pmordo.presentation.models
data class SellerSignUpDomainModel(
    val email: String,
    val username: String,
    val password: String,
    val inn: String,
    val companyName: String,
    val companyPosition: String,
    val userType: UserType,
)
