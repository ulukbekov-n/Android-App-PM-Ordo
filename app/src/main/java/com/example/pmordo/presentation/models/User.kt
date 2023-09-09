package com.example.pmordo.presentation.models

import java.util.UUID

data class User(
    var id: Long,
    var email: String,
    var username: String,
    var password: String? = null,
    var phoneNumber: String,
    var userPhoto: String? = null,
    var address: String,
    var birthday: String,
    var job: String,
    var specialization: String,
    var userWhatsapp: String,
    var userTelegram: String,
    var userType: UserType,
) {

    companion object {
        fun unknown() = User(
            id = UUID.randomUUID().toString().toLong(),
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
            userType = UserType.unknown,
        )
    }
}

enum class UserType {
    unknown,
    salesman,
    buyer,
}