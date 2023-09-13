package com.example.pmordo.presentation.mappers

import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserSignUpDomain
import com.example.pmordo.presentation.models.UserSignUp

class MapUserSignUpToDomain : Mapper<UserSignUp, UserSignUpDomain>  {
    override fun map(from: UserSignUp) = from.run {
        UserSignUpDomain(
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