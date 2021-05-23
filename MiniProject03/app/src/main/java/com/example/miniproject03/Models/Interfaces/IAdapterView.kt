package com.example.miniproject03.Models.Interfaces

interface IAdapterView {
    fun addItem(item: Any)
    val onClickListener: OnClickListener

}