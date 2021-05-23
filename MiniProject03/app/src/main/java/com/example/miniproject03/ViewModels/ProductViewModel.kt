package com.example.miniproject03.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.miniproject03.Models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            room.productDao().insert(product)
        }
    }

    fun addToCart(title:String, price:Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val length = room.productCartDao().getDataCount()
            room.productCartDao().insert(ProductCart(length+1,price,title))
        }
    }
    fun deleteCart(product: ProductCart)
    {
        viewModelScope.launch(Dispatchers.IO) {
            room.productCartDao().delete(product)
        }
    }



    var myCart: LiveData<MutableList<ProductCart>>
    var productRepository: ProductRepository
    val  productCartRepository:ProductCartRepository
    var myProduct: LiveData<List<Product>>
    lateinit var app :Application

    val room: AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "products").build()
    init{
        val productDao = room.productDao()
        val productCartDao = room.productCartDao()
        productRepository = ProductRepository(productDao)
        productCartRepository = ProductCartRepository(productCartDao)
        myProduct = productRepository.allProduct
        myCart = productCartRepository.allProduct



    }
}
