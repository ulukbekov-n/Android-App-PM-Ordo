package com.example.pmordo.domain.models

data class SellerSignUpDomain(
    val email: String,
    val username: String,
    val password: String,
    val inn: String,
    val companyName: String,
    val companyPosition: String
)
