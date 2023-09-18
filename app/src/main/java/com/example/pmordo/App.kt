package com.example.pmordo

import android.app.Application
import com.example.pmordo.presentation.di.appModule
import com.example.pmordo.presentation.di.networkModule
import com.example.pmordo.presentation.di.repositoryModule
import com.example.pmordo.presentation.di.useCaseModule
import com.example.pmordo.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        instance = this

        startKoin {
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    networkModule
                )
            )
        }
    }
    companion object {
        lateinit var instance: App
            private set
    }
}

