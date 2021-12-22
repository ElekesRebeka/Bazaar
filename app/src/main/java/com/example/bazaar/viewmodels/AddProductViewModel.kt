package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.MyApplication
import com.example.bazaar.model.AddProductRequest
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.model.Product
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository

class AddProductViewModel (val repository: Repository) : ViewModel() {
    var product = MutableLiveData<Product>()

    init {
        Log.d("xxx", "AddProductViewModel constructor - Token: ${MyApplication.token}")
        product.value = Product()
    }

    suspend fun addProduct() {
        Log.d("xxx","add product: "+ product.value.toString())
        val request = AddProductRequest(is_active = product.value!!.is_active, price_per_unit = product.value!!.price_per_unit,
            units = product.value!!.units, title = product.value!!.title, description = product.value!!.description)
        try {
            val result = repository.addProduct(MyApplication.token, request.title, request.description, request.price_per_unit, request.units, request.is_active, "","", 4.0)
        } catch (e: Exception) {
            Log.d("xxx", "AddProductViewModel exception: ${e.toString()}")
        }
    }

    fun getProductDetailsToPreview(): Product? {
        return product.value
    }
}