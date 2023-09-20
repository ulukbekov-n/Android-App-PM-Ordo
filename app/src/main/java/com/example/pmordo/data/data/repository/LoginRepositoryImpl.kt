package com.example.pmordo.data.data.repository

import com.example.pmordo.data.base.BaseApiResponse
import com.example.pmordo.data.base.BaseRepository
import com.example.pmordo.data.data.mappers.MapSellerDomainToDataMapper
import com.example.pmordo.data.data.models.SignUpResponseDataModel
import com.example.pmordo.data.data.models.UserSignUpData
import com.example.pmordo.data.data.service.AuthApi
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.base.RequestState
import com.example.pmordo.domain.models.SellerSignUpDomain
import com.example.pmordo.domain.models.SignUpResponseDomainModel
import com.example.pmordo.domain.models.UserSignUpDomain
import com.example.pmordo.domain.repository.LoginRepository


class LoginRepositoryImpl(
    private val authApi: AuthApi,
    private val mapUserDomainToDataMapper: Mapper<UserSignUpDomain, UserSignUpData>,
    private val mapSellerDomainToDataMapper: MapSellerDomainToDataMapper,
    private val mapUserResponseDataToDomainMapper: Mapper<SignUpResponseDataModel, SignUpResponseDomainModel>,
) : LoginRepository, BaseApiResponse(), BaseRepository {
    override suspend fun signUp(user: UserSignUpDomain): RequestState<SignUpResponseDomainModel> {
        val result = safeApiCalll { authApi.signUp(request = mapUserDomainToDataMapper.map(user)) }
        return renderResult(result = result).map(mapUserResponseDataToDomainMapper)
    }
    override suspend fun signUpSeller(seller: SellerSignUpDomain): RequestState<SignUpResponseDomainModel> {
        val result = safeApiCalll { authApi.signUpSeller(request = mapSellerDomainToDataMapper.map(seller)) }
            return renderResult(result = result).map(mapUserResponseDataToDomainMapper)
    }



}