package com.example.pmordo.presentation.models

data class SellerSignUp(
    val email: String = String(),
    val username: String = String(),
    val password: String = String(),
    val INN: String = String(),
    val companyName: String = String(),
    val companyPosition: String = String(),
    val userType: String = String(),
    val role: String = String()
) {

}
