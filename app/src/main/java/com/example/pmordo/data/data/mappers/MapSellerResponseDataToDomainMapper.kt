package com.example.pmordo.data.data.mappers

import com.example.pmordo.data.data.models.SignUpResponseDataModel
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SignUpResponseDomainModel

class MapSellerResponseDataToDomainMapper :
    Mapper<SignUpResponseDataModel, SignUpResponseDomainModel> {
    override fun map(from: SignUpResponseDataModel) = from.run {
        SignUpResponseDomainModel(
            profileId = profileId
        )
    }
}
