package com.example.miniproject03.Retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    val URL = "https://fakestoreapi.com/"
    val okHttp = OkHttpClient.Builder()

    val builder = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).client(
        okHttp.build())
    val retrofit = builder.build()

    fun <T> buildService (serviceType : Class<T>):T{
        return retrofit.create(serviceType)
    }
}