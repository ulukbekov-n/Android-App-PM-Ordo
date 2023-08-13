package com.example.pmordo.di

import com.example.pmordo.presentation.firstscreen.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule= module {
    viewModel{
        LoginViewModel(loginUserUseCase = get())
    }
}
