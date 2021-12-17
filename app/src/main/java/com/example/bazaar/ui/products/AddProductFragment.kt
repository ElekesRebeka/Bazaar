package com.example.bazaar.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.bazaar.R

class AddProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var addProduct:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        addProduct = view.findViewById(R.id.launch)
        addProduct.setOnClickListener{
            
        }
        return view
    }

    companion object {

    }
}