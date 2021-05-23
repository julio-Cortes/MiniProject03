package com.example.miniproject03.Models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Product(
    val category: String,
    val description: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
): Parcelable{

}

@Parcelize
@Entity
data class ProductCart(
    @PrimaryKey
    val id: Int,
    val price: Double,
    val title: String
): Parcelable{

}