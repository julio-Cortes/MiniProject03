package com.example.miniproject03.Views

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.miniproject03.Models.Interfaces.OnClickListener
import com.example.miniproject03.Models.Product
import com.example.miniproject03.R
import com.example.miniproject03.Retrofit.ProductService
import com.example.miniproject03.Retrofit.ServiceBuilder
import com.example.miniproject03.ViewModels.ProductViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() , OnClickListener{

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyProductRecyclerViewAdapter
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        setHasOptionsMenu(true)
        recyclerView = view.findViewById(R.id.list)
        adapter = MyProductRecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        loadProducts()
        viewModel.myProduct.observe(this, Observer{ product ->
            adapter.loadProducts(product)
        })
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater :MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onClickItem(item: Any) {
        val action = ProductFragmentDirections.actionProductFragmentToProductDetail(item as Product)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view?.let { Navigation.findNavController(it).navigate(R.id.action_productFragment_to_cartFragment) }
        return true
    }
    fun loadProducts(){
        val destinationService  = ServiceBuilder.buildService(ProductService::class.java)
        val requestCall =destinationService.getProducts()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val productlist  = response.body()!!
                    Log.d("Response", "productlist size : ${productlist.size}")
                    adapter.loadProducts( response.body()!!)
                    adapter.values.forEach{
                        viewModel.addProduct(it)

                    }
                }else{
                    println("Something went wrong ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println("Something went wrong $t")
            }
        })
    }


}