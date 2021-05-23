package com.example.miniproject03.Models

import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {
    val allProduct: LiveData<List<Product>> = productDao.getAll()

    suspend fun addProduct(product: Product)
    {
        productDao.insert(product)
    }
}

class ProductCartRepository(private val productCartDao: ProductCartDao) {
    val allProduct: LiveData<MutableList<ProductCart>> = productCartDao.getAll()

    suspend fun addProduct(product: ProductCart)
    {
        productCartDao.insert(product)
    }
    suspend fun deleteProduct(product: ProductCart) {
        productCartDao.delete(product)
    }
}