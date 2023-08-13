package com.example.pmordo

import android.app.Application
import com.example.pmordo.di.dataModule
import com.example.pmordo.di.domainModule
import com.example.pmordo.di.networkModule
import com.example.pmordo.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule,
                    networkModule
                )
            )
        }
    }
}