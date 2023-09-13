package com.example.pmordo.presentation.mappers

import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserDomain
import com.example.pmordo.presentation.models.User

class MapUserToDomain : Mapper<User, UserDomain> {
    override fun map(from: User) = from.run {
        if (from == User.unknown()) UserDomain.unknown()
        UserDomain(
            id = id,
            email = email,
            username = username,
            password = password,
            phoneNumber = phoneNumber,
            userPhoto = userPhoto,
            address = address,
            birthday = birthday,
            job = job,
            specialization = specialization,
            userWhatsapp = userWhatsapp,
            userTelegram = userTelegram,
            userType = userType.name,
        )
    }
}