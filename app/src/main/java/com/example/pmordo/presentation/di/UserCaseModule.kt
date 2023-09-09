package com.example.pmordo.presentation.di

import com.example.pmordo.domain.usecase.UserLoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        UserLoginUseCase(repository = get())
    }
}