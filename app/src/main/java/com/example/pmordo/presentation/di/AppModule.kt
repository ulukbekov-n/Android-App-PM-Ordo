package com.example.pmordo.presentation.di

import SellerSignUpToSellerSignUpDomainMapper
import com.example.pmordo.data.base.ResourceProvider
import com.example.pmordo.data.data.repository.ProductRepository
import com.example.pmordo.domain.base.DispatchersProvider
import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SellerSignUpDomain
import com.example.pmordo.presentation.base.BaseResourceProvider
import com.example.pmordo.presentation.models.SellerSignUp
import com.example.pmordo.presentation.ui.register.seller.SellerRegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DispatchersProvider> { DispatchersProvider.Base() }

    single<ResourceProvider> { ResourceProvider.Base() }

    single<BaseResourceProvider> { BaseResourceProvider.Base() }
    single<Mapper<SellerSignUp, SellerSignUpDomain>> { SellerSignUpToSellerSignUpDomainMapper() }

    val repositoryModule = module {
        single { ProductRepository(productApi = get()) }
    }
    viewModel { SellerRegisterViewModel(get(), get(), get(), get(), get()) }

}