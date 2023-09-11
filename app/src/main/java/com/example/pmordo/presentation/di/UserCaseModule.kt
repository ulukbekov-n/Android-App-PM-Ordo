package com.example.pmordo.presentation.di

import com.example.pmordo.domain.usecase.UserUseCase
import com.example.pmordo.domain.usecase.UserUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {


    single<UserUseCase> {
        UserUseCaseImpl(
            repository = get(),
        )
    }

}