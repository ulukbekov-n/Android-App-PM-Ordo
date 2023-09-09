package com.example.pmordo.presentation.di

import com.example.pmordo.data.base.ResourceProvider
import com.example.pmordo.domain.base.DispatchersProvider
import com.example.pmordo.presentation.base.BaseResourceProvider
import org.koin.dsl.module

val appModule = module {

    single<DispatchersProvider> { DispatchersProvider.Base() }

    single<ResourceProvider> { ResourceProvider.Base(get()) }

    single<BaseResourceProvider> { BaseResourceProvider.Base(get()) }

}