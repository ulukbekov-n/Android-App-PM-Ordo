package com.example.pmordo.di

import com.example.pmordo.data.repositoryImple.UserRepositoryImple
import com.example.pmordo.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule= module {
    single<UserRepository>{
        UserRepositoryImple(api = get())
    }
}