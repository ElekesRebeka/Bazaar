package com.example.bazaar.ui.products

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.AddProductViewModel
import com.example.bazaar.viewmodels.AddProductViewModelFactory
import com.example.bazaar.viewmodels.LoginViewModel
import com.example.bazaar.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch

class AddProductFragment : Fragment() {
    val factoryAddProduct = AddProductViewModelFactory(Repository())
    private val addProductViewModel: AddProductViewModel by lazy{
        ViewModelProvider(requireActivity(),factoryAddProduct).get((AddProductViewModel::class.java))
    }

    private lateinit var addProduct:Button
    private lateinit var previewButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        val spinner: Spinner = view.findViewById(R.id.priceSpinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.price_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val spinner2: Spinner = view.findViewById(R.id.amountSpinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.amount_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }

        val title: EditText = view.findViewById(R.id.title)
        val price: EditText = view.findViewById(R.id.price)
        val amount: EditText = view.findViewById(R.id.amount)
        val description: EditText = view.findViewById(R.id.description)
        val active: Switch = view.findViewById(R.id.switch1)
        addProduct = view.findViewById(R.id.launch)
        previewButton = view.findViewById(R.id.preview)

        addProduct.setOnClickListener{
            addProductViewModel.product.value.let {
                if (it != null) {
                    it.title = title.text.toString()
                }
                if (it != null) {
                    it.price_per_unit = price.text.toString()
                }
                if (it != null) {
                    it.units = amount.text.toString()
                }
                if (it != null) {
                    it.description = description.text.toString()
                }
                if (it != null) {
                    it.is_active = active.isChecked
                }
                if (it != null) {
                    it.amount_type = spinner.toString()
                }
                if (it != null) {
                    it.price_type = spinner2.toString()
                }
            }
            lifecycleScope.launch {
                addProductViewModel.addProduct()
            }
            findNavController().navigate(R.id.action_addProductFragment_to_listFragment)
        }
        previewButton.setOnClickListener{
            findNavController().navigate(R.id.action_addProductFragment_to_productDetailFragment)
        }
        return view
    }

    companion object {

    }
}