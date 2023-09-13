package com.example.pmordo.data.data.mappers

import com.example.pmordo.data.data.models.UserSaveModel
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.UserDomain

class MapUserSaveToDomainModel : Mapper<UserSaveModel, UserDomain> {
    override fun map(from: UserSaveModel): UserDomain = from.run {
        if (from == UserSaveModel.unknown()) {
            UserDomain.unknown()
        } else UserDomain(
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
            userType = userType,
        )
    }
}