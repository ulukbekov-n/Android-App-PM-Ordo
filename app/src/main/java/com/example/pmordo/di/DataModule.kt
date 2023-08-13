package com.example.pmordo.di

import com.example.pmordo.data.repositoryImple.UserRepositoryImple
import com.example.pmordo.domain.repository.UserRepository
import org.koin.dsl.module
import java.lang.reflect.Array.get

val dataModule= module {
    single<UserRepository>{
        UserRepositoryImple(api = get())
    }
}