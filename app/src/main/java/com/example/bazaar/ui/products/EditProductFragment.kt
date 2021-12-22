package com.example.bazaar.ui.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.fragment.app.activityViewModels
import com.example.bazaar.MainActivity
import com.example.bazaar.R
import com.example.bazaar.model.Product
import com.example.bazaar.viewmodels.ListViewModel

class EditProductFragment : Fragment() {
    lateinit var filteredList: List<Product>
    private lateinit var title: EditText
    private lateinit var price_per_unit: EditText
    private lateinit var is_active: Switch
    private lateinit var rating: EditText
    private lateinit var amount_type: EditText
    private lateinit var price_type: EditText
    private lateinit var editButton: Button
    private lateinit var product_id: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_product, container, false)
        val model: ListViewModel by activityViewModels()
        val username = MainActivity.sharedPreferences.getStringValue("logged_in_user_name","")

        title = view.findViewById(R.id.title)
        price_per_unit = view.findViewById(R.id.price)
        is_active = view.findViewById(R.id.switch1)
        amount_type = view.findViewById(R.id.amount)
        rating = view.findViewById(R.id.rating)
        price_type = view.findViewById(R.id.pricetype)
        editButton = view.findViewById(R.id.update)

        model.products.observe(viewLifecycleOwner){
            filteredList = model.products.value?.filter { it.username == username }!!
            Log.d("xxx","List {$filteredList}")
            val pos = MainActivity.sharedPreferences.getStringValue("my_edit_product","")
            if (pos != null) {
                val actualProduct = filteredList[pos.toInt()]
                title.setText(actualProduct.title)
                price_per_unit.setText(actualProduct.price_per_unit)
                is_active.isChecked = actualProduct.is_active
                amount_type.setText(actualProduct.amount_type)
                rating.setText(actualProduct.rating.toString())
                price_type.setText(actualProduct.price_type)
                product_id = actualProduct.product_id
            }
        }

        editButton.setOnClickListener(){
            //NOT IMPLEMENTED YET
        }

        return view
    }

    companion object {

    }
}