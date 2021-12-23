package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MyApplication
import com.example.bazaar.model.Order
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class OrdersListViewModel (private val repository: Repository) : ViewModel() {
    var orders: MutableLiveData<List<Order>> = MutableLiveData()

    init{
        //Log.d("xxx", "OrdersListViewModel constructor - Token: ${MyApplication.token}")
        getOrders()
    }

    fun getOrders() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getOrders(MyApplication.token)
                orders.value = result.orders
                Log.d("xxx", "OrdersListViewModel - #orders:  ${result.item_count}")
            }catch(e: Exception){
                Log.d("xxx", "OrdersListViewModel exception: ${e.toString()}")
            }
        }
    }
}