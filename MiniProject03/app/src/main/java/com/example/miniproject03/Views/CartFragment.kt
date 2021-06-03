package com.example.miniproject03.Views

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.miniproject03.R
import com.example.miniproject03.ViewModels.ProductViewModel
import org.koin.android.ext.android.inject


class CartFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapter =MyProductCartRecyclerViewAdapter()
    private val viewModel: ProductViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart_list, container, false)
        adapter=   MyProductCartRecyclerViewAdapter()
        setHasOptionsMenu(true)
        recyclerView = view.findViewById(R.id.list)
        recyclerView.adapter = adapter
        val totalTextView = view.findViewById<TextView>(R.id.total)
        val button = view.findViewById<Button>(R.id.see_map_button)
        viewModel.myCart.observe(this, Observer {
            viewModel.updateData(it)
        })

        button.setOnClickListener{
            println(viewModel.cart)
            val directons = CartFragmentDirections.actionCartFragmentToMapsFragment(viewModel.cart.toTypedArray())
            view?.let { Navigation.findNavController(it).navigate(directons) }
        }


        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)


        viewModel.myCart.observe(this, Observer{ product ->
            adapter.total = 0.0
            adapter.loadProducts(product )
            totalTextView.setText("TOTAL$:${adapter.total.toString()}")
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater : MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return true
    }
    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.deleteCart((adapter.values[viewHolder.adapterPosition]))
            adapter.values.removeAt(viewHolder.adapterPosition)
            adapter.notifyDataSetChanged()
        }
    }

}