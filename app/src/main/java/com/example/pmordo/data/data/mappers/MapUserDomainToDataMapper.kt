package com.example.pmordo.data.data.mappers

import com.example.pmordo.data.data.models.UserSignUpData
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserSignUpDomain

class MapUserDomainToDataMapper : Mapper<UserSignUpDomain, UserSignUpData> {
    override fun map(from: UserSignUpDomain) = from.run {
        UserSignUpData(
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
            userType = userType,
        )
    }
}