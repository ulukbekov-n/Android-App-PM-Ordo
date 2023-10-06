package com.example.pmordo.data.data.mappers

import com.example.pmordo.data.data.models.SellerSaveModel
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SellerDomain

class MapSellerDomainToSaveMapper : Mapper<SellerDomain, SellerSaveModel> {
    override fun map(from: SellerDomain) = from.run {
        SellerSaveModel(
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
            INN = INN,
            companyName = companyName,
            companyPosition = companyPosition,
            role = role
        )
    }
}
