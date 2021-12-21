package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.bazaar.MyApplication
import com.example.bazaar.repository.Repository
import com.example.bazaar.model.Product
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository) : ViewModel() {
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var actualItemPosition: MutableLiveData<Int> = MutableLiveData()

    init{
        Log.d("xxx", "ListViewModel constructor - Token: ${MyApplication.token}")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProducts(MyApplication.token)
                products.value = result.products
                Log.d("xxx", "ListViewModel - #products:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "ListViewModel exception: ${e.toString()}")
            }
        }
    }
}