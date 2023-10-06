package com.example.pmordo.data.data.mappers

import com.example.pmordo.data.data.models.SellerSignUpData // Replace with your actual data model for sellers
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SellerSignUpDomain // Replace with your actual domain model for sellers

class MapSellerDomainToDataMapper : Mapper<SellerSignUpDomain, SellerSignUpData> {
    override fun map(from: SellerSignUpDomain): SellerSignUpData = from.run {

        SellerSignUpData(
            email = email,
            username = username,
            password = password,
            INN = INN,
            companyName = companyName,
            companyPosition = companyPosition,
            role = role
        )
    }
}
