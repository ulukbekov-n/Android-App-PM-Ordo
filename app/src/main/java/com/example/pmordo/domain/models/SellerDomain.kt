package com.example.pmordo.domain.models

import java.util.UUID

data class SellerDomain(
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
    var INN: String,
    var companyName: String,
    var companyPosition: String,
    var role:String
) {

    companion object {
        fun unknown() = SellerDomain(
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
            userType = String(),
            INN = String(),
            companyName = String(),
            companyPosition = String(),
            role = String()
        )
    }
}
