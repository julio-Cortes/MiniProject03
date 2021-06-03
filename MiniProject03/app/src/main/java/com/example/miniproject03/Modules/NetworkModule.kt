package com.example.miniproject03.Modules

import com.example.miniproject03.Retrofit.ServiceBuilder
import org.koin.dsl.module

val networkModule = module {
    single { ServiceBuilder.getOkClient() }
    single { ServiceBuilder.getRetrofit(get()) }
    single { ServiceBuilder.ProductApi(get())}
}