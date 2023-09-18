package com.example.pmordo.presentation.di

import com.example.pmordo.data.base.ResourceProvider
import com.example.pmordo.domain.base.DispatchersProvider
import com.example.pmordo.presentation.base.BaseResourceProvider
import com.example.pmordo.presentation.ui.register.seller.SellerRegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.reflect.Array.get

val appModule = module {

    single<DispatchersProvider> { DispatchersProvider.Base() }

    single<ResourceProvider> { ResourceProvider.Base() }

    single<BaseResourceProvider> { BaseResourceProvider.Base() }
    viewModel { SellerRegisterViewModel(get(), get(), get(), get(), get()) }

}