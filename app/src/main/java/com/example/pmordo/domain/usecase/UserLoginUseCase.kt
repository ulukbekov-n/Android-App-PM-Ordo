package com.example.pmordo.domain.usecase

import com.example.pmordo.domain.models.UserSignUpDomain
import com.example.pmordo.domain.repository.LoginRepository


interface UserUseCase {
    suspend operator fun invoke(user: UserSignUpDomain)

}

class UserUseCaseImpl(private val repository: LoginRepository) : UserUseCase {
    override suspend fun invoke(user: UserSignUpDomain) {
        repository.signUp(user)
    }

}