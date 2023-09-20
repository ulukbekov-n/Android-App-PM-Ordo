package com.example.pmordo.presentation.mappers

import android.net.Uri
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserDomain
import com.example.pmordo.presentation.models.User
import com.example.pmordo.presentation.models.UserType

class MapUserDomainToUser : Mapper<UserDomain, User> {
    override fun map(from: UserDomain) = from.run {
        User(
            id = id,
            email = email,
            username = username,
            password = password,
            phoneNumber = phoneNumber,
            userPhoto = Uri.EMPTY,
            address = address,
            birthday = birthday,
            job = job,
            specialization = specialization,
            userWhatsapp = userWhatsapp,
            userTelegram = userTelegram,
            userType = userType(userType),
        )
    }

    private fun userType(userType: String): UserType =
        when (userType) {
            "A" -> UserType.salesman
            " " -> UserType.buyer
            else -> UserType.unknown
        }
}