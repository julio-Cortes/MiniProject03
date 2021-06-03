package com.example.miniproject03.Views

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.miniproject03.Models.Product
import com.example.miniproject03.Models.ProductCart
import com.example.miniproject03.R



class MyProductCartRecyclerViewAdapter(
) : RecyclerView.Adapter<MyProductCartRecyclerViewAdapter.ViewHolder>() {
    var values = mutableListOf<ProductCart>()!!
    var total = 0.0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_cart, parent, false)
        val total = 0
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position)
        if (item != null) {
            holder.idView.text = item.title
        }
        if (item != null) {
            holder.contentView.text = item.price.toString()
        }
    }

    override fun getItemCount(): Int = values.size!!

    fun loadProducts(products: MutableList<ProductCart>) {
        values = products
        values.forEach{
            total+=it.price
        }

        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_title_cart)
        val contentView: TextView = view.findViewById(R.id.item_price_cart)

    }
}