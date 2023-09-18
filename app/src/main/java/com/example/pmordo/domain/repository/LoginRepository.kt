package com.example.pmordo.domain.repository

import com.example.pmordo.domain.base.RequestState
import com.example.pmordo.domain.models.SellerSignUpDomain
import com.example.pmordo.domain.models.SignUpResponseDomainModel
import com.example.pmordo.domain.models.UserSignUpDomain
import com.example.pmordo.presentation.models.SellerSignUpDomainModel

interface LoginRepository {
    suspend fun signUp(user: UserSignUpDomain): RequestState<SignUpResponseDomainModel>
    suspend fun signUpSeller(seller: SellerSignUpDomain): RequestState<SignUpResponseDomainModel>

}