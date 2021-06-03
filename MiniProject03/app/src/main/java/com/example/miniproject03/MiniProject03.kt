package com.example.miniproject03

import android.app.Application

import com.example.miniproject03.Modules.appModule

import com.example.miniproject03.Modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MiniProject03: Application(){
    override fun onCreate(){{}
        super.onCreate()
        startKoin{
            androidContext(this@MiniProject03)
            modules(listOf(appModule, networkModule ))
        }
    }
}