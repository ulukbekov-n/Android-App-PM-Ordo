package com.example.pmordo.domain.models

import java.util.UUID

data class UserDomain(
    var id: Long,
    var email: String,
    var username: String,
    var password: String,
    var phoneNumber: String,
    var userPhoto: String,
    var address: String,
    var birthday: String,
    var job: String,
    var specialization: String,
    var userWhatsapp: String,
    var userTelegram: String,
    var userType: String,
) {

    companion object {
        fun unknown() = UserDomain(
            id = UUID.randomUUID().mostSignificantBits,
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
            userType = String()
        )
    }
}