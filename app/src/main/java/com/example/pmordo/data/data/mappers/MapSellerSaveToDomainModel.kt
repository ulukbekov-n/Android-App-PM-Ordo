package com.example.pmordo.data.data.mappers

import com.example.pmordo.data.data.models.SellerSaveModel
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SellerDomain

class MapSellerSaveToDomainModel : Mapper<SellerSaveModel, SellerDomain> {
    override fun map(from: SellerSaveModel): SellerDomain = from.run {
        if (from == SellerSaveModel.unknown()) {
            SellerDomain.unknown()
        } else SellerDomain(
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
            inn = inn,
            companyName = companyName,
            companyPosition = companyPosition
        )
    }
}
