package com.example.miniproject03.Models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * from Product")
    fun getAll(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product?)
}
@Dao
interface ProductCartDao{
    @Query("SELECT * from ProductCart")
    fun getAll(): LiveData<MutableList<ProductCart>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: ProductCart?)

    @Delete
    fun delete(product: ProductCart)

    @Query("SELECT COUNT(id) FROM ProductCart")
    fun getDataCount(): Int
}