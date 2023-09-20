package com.example.pmordo.presentation.di

import com.example.pmordo.data.data.mappers.*
import com.example.pmordo.data.data.repository.LoginRepositoryImpl
import com.example.pmordo.data.data.repository.UserCacheRepositoryImpl
import com.example.pmordo.domain.repository.LoginRepository
import com.example.pmordo.domain.repository.UserCacheRepository
import org.koin.dsl.module
val repositoryModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(
            authApi = get(),
            mapUserDomainToDataMapper = MapUserDomainToDataMapper(),
            mapUserResponseDataToDomainMapper = MapUserResponseDataToDomainMapper(),
            mapSellerDomainToDataMapper = MapSellerDomainToDataMapper()
        )
    }

    single<UserCacheRepository> {
        UserCacheRepositoryImpl(
            mapUserSaveToDomainModel = MapUserSaveToDomainModel(),
            mapUserDomainToSave = MapUserDomainToSave()
        )
    }
}
