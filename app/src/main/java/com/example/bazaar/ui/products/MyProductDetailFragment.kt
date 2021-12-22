package com.example.bazaar.ui.products

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bazaar.MainActivity
import com.example.bazaar.R
import com.example.bazaar.model.Product
import com.example.bazaar.viewmodels.ListViewModel
import java.text.SimpleDateFormat
import java.util.*

class MyProductDetailFragment : Fragment() {
    lateinit var filteredList: List<Product>

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd")
        return format.format(date)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_product_detail, container, false)
        val pos = MainActivity.sharedPreferences.getStringValue("my_product_pos","")
        val username = MainActivity.sharedPreferences.getStringValue("logged_in_user_name","")
        val model: ListViewModel by activityViewModels()

        val sellerTextView : TextView = view.findViewById(R.id.seller)
        val dateTextView : TextView = view.findViewById(R.id.date)
        val productName : TextView = view.findViewById(R.id.productName)
        val priceTextView : TextView = view.findViewById(R.id.price)
        val activeTextView : TextView = view.findViewById(R.id.active)
        val descriptionTextView : TextView = view.findViewById(R.id.description)
        val availableAmount: TextView = view.findViewById(R.id.textView7)

        model.products.observe(viewLifecycleOwner) {
            filteredList = model.products.value?.filter { it.username == username }!!
            Log.d("xxx", "List {$filteredList}")
            if (pos != null) {
                val actualProduct = filteredList[pos.toInt()]
                val dateString = convertLongToTime(actualProduct.creation_time!!.toLong())

                sellerTextView.text=actualProduct.username
                dateTextView.text=dateString
                productName.text=actualProduct.title
                priceTextView.text=actualProduct.price_per_unit
                if(actualProduct.is_active){
                    activeTextView.text="Active"
                }
                else{
                    activeTextView.text="Inactive"
                    activeTextView.setTextColor(Color.parseColor("#9A9A9A"));
                }

                descriptionTextView.text=actualProduct.description
                availableAmount.text=actualProduct.units
            }

        }

        //user click
        sellerTextView.setOnClickListener(){
            MainActivity.sharedPreferences.putStringValue("seller", sellerTextView.text.toString())
            findNavController().navigate(R.id.action_myProductDetailFragment_to_profileViewFragment)
        }

        return view
    }

    companion object {

    }
}