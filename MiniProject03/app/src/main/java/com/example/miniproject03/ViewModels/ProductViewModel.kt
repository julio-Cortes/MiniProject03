package com.example.miniproject03.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.miniproject03.Models.*
import com.example.miniproject03.Retrofit.ProductRemoteRepository
import com.example.miniproject03.Views.MyProductCartRecyclerViewAdapter
import com.example.miniproject03.Views.MyProductRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel(application: Application, productRemoteRepository: ProductRemoteRepository,
                       productCartRepository: ProductCartRepository, productRepository: ProductRepository,
                       room: AppDatabase): AndroidViewModel(application) {

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.addProduct(product)
        }
    }

    fun addToCart(title: String, price: Double, latitud: Double, longitude: Double) {

        viewModelScope.launch(Dispatchers.IO) {
            val length = room.productCartDao().getDataCount()
            productCartRepository.addProduct(ProductCart(length+1,price,title,longitude,latitud))

        }
    }
    fun deleteCart(product: ProductCart)
    {
        viewModelScope.launch(Dispatchers.IO) {
            productCartRepository.deleteProduct(product)
        }
    }

    fun updateData(it: MutableList<ProductCart>?) {
        if (it != null) {
            cart = it
        }

    }
    fun loadData(adapter:MyProductRecyclerViewAdapter){
        val requestCall = productRemoteRepository.getProducts()
        requestCall.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val productlist  = response.body()!!
                    Log.d("Response", "productlist size : ${productlist.size}")
                    adapter.loadProducts( response.body()!!)
                    adapter.values.forEach{
                        addProduct(it)

                    }
                }else{
                    println("Something went wrong ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println("Something went wrong $t")
            }
        })
    }

    var cart = mutableListOf<ProductCart>()
    var myCart: LiveData<MutableList<ProductCart>>

    var myProduct: LiveData<List<Product>>
    val room = room
    val productRemoteRepository = productRemoteRepository
    val productRepository = productRepository
    val productCartRepository = productCartRepository

    init{
        myProduct = productRepository.allProduct
        myCart = productCartRepository.allProduct


    }
}
