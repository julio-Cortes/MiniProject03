package com.example.miniproject03.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceBuilder {
    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }
    //Este es el cliente que funciona por detras de retrofit
    fun getOkClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(makeLoggingInterceptor(isDebug = true))
            .build()
    }


    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
    fun ProductApi(retrofit: Retrofit): ProductRemoteRepository = retrofit.create(ProductRemoteRepository::class.java)
}
