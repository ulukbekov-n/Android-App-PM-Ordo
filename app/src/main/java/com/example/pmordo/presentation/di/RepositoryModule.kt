package com.example.pmordo.presentation.di

import com.example.pmordo.data.data.mappers.MapUserDomainToDataMapper
import com.example.pmordo.data.data.mappers.MapUserDomainToSave
import com.example.pmordo.data.data.mappers.MapUserResponseDataToDomainMapper
import com.example.pmordo.data.data.mappers.MapUserSaveToDomainModel
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
            mapUserResponseDataToDomainMapper = MapUserResponseDataToDomainMapper()
        )
    }
    single<UserCacheRepository> {
        UserCacheRepositoryImpl(
            context = get(),
            mapUserSaveToDomainModel = MapUserSaveToDomainModel(),
            mapUserDomainToSave = MapUserDomainToSave()
        )
    }
}