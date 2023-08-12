package com.example.pmordo.di

import com.example.pmordo.domain.usecase.UserLoginUseCase
import org.koin.dsl.module

val domainModule= module {
    factory {
        UserLoginUseCase(repository = get())
    }
}