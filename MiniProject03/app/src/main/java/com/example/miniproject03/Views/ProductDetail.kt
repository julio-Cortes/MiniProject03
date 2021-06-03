package com.example.miniproject03.Views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.miniproject03.R
import com.example.miniproject03.Service.LocationService
import com.example.miniproject03.ViewModels.ProductViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject


class ProductDetail : Fragment() {
    val args : ProductDetailArgs by navArgs()
    private val viewModel: ProductViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)
        val imageView = view.findViewById<ImageView>(R.id.product_image_detail)
        val titleTextView = view.findViewById<TextView>(R.id.product_title_detail)
        val priceTextView = view.findViewById<TextView>(R.id.product_price_detail)
        val descriptionTextView = view.findViewById<TextView>(R.id.product_description_detail)
        val product = args.product
        Picasso.get().load(product.image).into(imageView)
        titleTextView.setText(product.title)
        priceTextView.setText(product.price.toString())
        descriptionTextView.setText(product.description)
        val button = view.findViewById<Button>(R.id.add_to_cart_button)
        button.setOnClickListener{
            this.getLocation(product.title,product.price)

            activity?.onBackPressed()
        }

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater : MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_productDetail_to_cartFragment) }
        return true
    }

    fun getLocation(title: String, price: Double) {
        val list = mutableListOf<Double>()
        LocationService.getLocation().observe(this, {
            list.add(it.latitude)
            list.add(it.longitude)
            viewModel.addToCart(title,price,list[0],list[1])
        })

    }

}