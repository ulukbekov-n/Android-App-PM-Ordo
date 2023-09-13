package com.example.pmordo.domain.repository

import com.example.pmordo.domain.base.RequestState
import com.example.pmordo.domain.models.SignUpResponseDomainModel
import com.example.pmordo.domain.models.UserSignUpDomain

interface LoginRepository {
//    suspend fun loginUser(userLoginRequest: LoginRequest)
//    suspend fun signUp(request: SignUpModel): ResultModel<SignUpResponseModel>

    suspend fun signUp(user: UserSignUpDomain): RequestState<SignUpResponseDomainModel>

}