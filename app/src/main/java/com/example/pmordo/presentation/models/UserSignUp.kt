package com.example.pmordo.presentation.models

import java.util.UUID

data class UserSignUp(
    val email: String = String(),
    val username: String = String(),
    val password: String = String(),
    val phoneNumber: String = String(),
    val userPhoto: String = String(),
    val address: String = String(),
    val birthday: String = String(),
    val job: String = String(),
    val specialization: String = String(),
    val userWhatsapp: String = String(),
    val userTelegram: String = String(),
    val userType: String = String(),
) {


    companion object {
        fun unknown() = UserSignUp(
            email = String(),
            username = String(),
            password = String(),
            phoneNumber = String(),
            userPhoto = String(),
            address = String(),
            birthday = String(),
            job = String(),
            userWhatsapp = String(),
            userTelegram = String(),
            specialization = String(),
            userType = String(),
        )
    }


    fun mapToUser(
        id: Long,
    ) = User(
        email = email,
        username = username,
        password = password,
        phoneNumber = phoneNumber,
        userPhoto = userPhoto,
        address = address,
        birthday = birthday,
        job = birthday,
        specialization = birthday,
        userWhatsapp = birthday,
        userTelegram = birthday,
        id = id,
        userType = UserType.valueOf(userType)
    )


}