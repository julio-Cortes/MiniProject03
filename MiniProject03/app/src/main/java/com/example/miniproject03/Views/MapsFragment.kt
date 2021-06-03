package com.example.miniproject03.Views

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.miniproject03.Models.ProductCart
import com.example.miniproject03.R
import com.example.miniproject03.ViewModels.ProductViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class MapsFragment : Fragment() {
    private val viewModel: ProductViewModel by inject()
    val args: MapsFragmentArgs by navArgs()
    private val callback = OnMapReadyCallback { googleMap ->
        if (args.cart.size !=0){
            val builder = LatLngBounds.Builder()

            args.cart?.forEach {
                val marker = LatLng(it.latitud,it.longtiud)
                googleMap.addMarker(MarkerOptions().position(marker).title(it.title))
                builder.include(marker)
            }

            val bounds = builder.build()
            val padding = 300 // offset from edges of the map in pixels

            val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            googleMap.animateCamera(cu);
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}