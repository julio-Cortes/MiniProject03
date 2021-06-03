package com.example.miniproject03.Modules

import androidx.room.Room
import com.example.miniproject03.Models.*
import com.example.miniproject03.ViewModels.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ProductViewModel(get(),get(),get(), get(), get()) }
    single {Room.databaseBuilder(get(), AppDatabase::class.java, "products").build() }
    single { get<AppDatabase>().productCartDao()}
    single { get<AppDatabase>().productDao()}
    single { ProductRepository(get())}
    single {ProductCartRepository(get())}

}