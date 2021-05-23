package com.example.miniproject03.Retrofit

import com.example.miniproject03.Models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getProducts () : Call<List<Product>>
}