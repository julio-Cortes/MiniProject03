package com.example.miniproject03.Views

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.miniproject03.Models.Interfaces.IAdapterView
import com.example.miniproject03.Models.Interfaces.OnClickListener
import com.example.miniproject03.Models.Product
import com.example.miniproject03.R
import com.squareup.picasso.Picasso


class MyProductRecyclerViewAdapter(
    override val onClickListener: OnClickListener
) : RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder>(),IAdapterView {

    var values = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.price.toString()
        holder.itemView.setOnClickListener{
            onClickListener.onClickItem(item)
        }
        return holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.product_name)
        val contentView: TextView = view.findViewById(R.id.product_price)
        val imageView = itemView.findViewById<ImageView>(R.id.product_image)


        fun bind(product: Product){
            idView.text = product.title
            contentView.text = product.price.toString()
            Picasso.get().load(product.image).into(imageView)
        }
    }

    override fun addItem(item: Any) {
        TODO("Not yet implemented")
    }
    fun loadProducts(products :List<Product>){
        values = products
        notifyDataSetChanged()
    }

}