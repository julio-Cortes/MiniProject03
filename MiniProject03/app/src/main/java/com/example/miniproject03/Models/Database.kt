package com.example.miniproject03.Models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class, ProductCart::class], version= 1, exportSchema = false)

abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun productCartDao(): ProductCartDao
}