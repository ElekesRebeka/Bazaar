package com.example.bazaar.ui.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.MainActivity
import com.example.bazaar.MyApplication
import com.example.bazaar.R
import com.example.bazaar.adapters.DataAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.ListViewModel
import com.example.bazaar.viewmodels.ListViewModelFactory
import android.content.res.ColorStateList
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*


class ProductDetailViewFragment : Fragment() {
    //lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val factory = ListViewModelFactory(Repository())
//        listViewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)
//        Log.d("xxx","ProductDetailViewFragment position: ${listViewModel.actualItemPosition.value}")
    }

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
        val view = inflater.inflate(R.layout.fragment_product_detail_view, container, false)

        //Log.d("xxx","ProductDetailViewFragment position: ${listViewModel.actualItemPosition.value}")
        //Log.d("xxx","ProductDetailViewFragment: ${listViewModel.products.value}")
        val user = MainActivity.sharedPreferences.getStringValue("actual_item_user", "")
        val date = MainActivity.sharedPreferences.getStringValue("actual_item_date", "")
        val name = MainActivity.sharedPreferences.getStringValue("actual_item_name", "")
        val price = MainActivity.sharedPreferences.getStringValue("actual_item_price","")
        val active = MainActivity.sharedPreferences.getStringValue("actual_item_is_active","")
        val amount = MainActivity.sharedPreferences.getStringValue("actual_item_amount", "")
        val description = MainActivity.sharedPreferences.getStringValue("actual_item_description","")
        Log.d("xxx","ProductDetailViewFragment: $user $date $name $price $active $amount $description")

        val sellerTextView :TextView = view.findViewById(R.id.seller)
        val dateTextView :TextView = view.findViewById(R.id.date)
        val productName :TextView = view.findViewById(R.id.productName)
        val priceTextView :TextView = view.findViewById(R.id.price)
        val activeTextView :TextView = view.findViewById(R.id.active)
        val descriptionTextView :TextView = view.findViewById(R.id.description)
        val availableAmount: TextView = view.findViewById(R.id.textView7)

        val dateString = convertLongToTime(date!!.toLong())

        sellerTextView.text=user
        dateTextView.text=dateString
        productName.text=name
        priceTextView.text=price
        if(active=="true"){
            activeTextView.text="Active"
        }
        else{
            activeTextView.text="Inactive"
            activeTextView.setTextColor(Color.parseColor("#9A9A9A"));
        }

        descriptionTextView.text=description
        availableAmount.text=amount
        return view
    }


    companion object {

    }
}