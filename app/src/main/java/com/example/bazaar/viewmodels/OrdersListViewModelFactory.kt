package com.example.bazaar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar.repository.Repository

class OrdersListViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OrdersListViewModel(repository) as T
    }
}