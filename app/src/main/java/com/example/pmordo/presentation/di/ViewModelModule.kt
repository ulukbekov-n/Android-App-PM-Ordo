package com.example.pmordo.presentation.di

import com.example.pmordo.presentation.mappers.MapUserDomainToUser
import com.example.pmordo.presentation.mappers.MapUserSignUpToDomain
import com.example.pmordo.presentation.mappers.MapUserToDomain
import com.example.pmordo.presentation.ui.home.HomeViewModel
import com.example.pmordo.presentation.ui.register.buyer.BuyerRegisterViewModel
import com.example.pmordo.presentation.ui.screen_splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SplashViewModel(
            mapper = MapUserDomainToUser(),
            userCacheRepository = get()
        )
    }
    viewModel {
        HomeViewModel( )
    }
    viewModel {
        BuyerRegisterViewModel(
            resourceProvider = get(),
            repository = get(),
            userCacheRepository = get(),
            dispatchersProvider = get(),
//            mapUserToDomain = MapUserToDomain(),
            mapUserSignUpToDomain = MapUserSignUpToDomain(),
        )
    }


}