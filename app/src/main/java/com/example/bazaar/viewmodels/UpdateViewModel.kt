package com.example.bazaar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MainActivity
import com.example.bazaar.MyApplication
import com.example.bazaar.MyApplication.Companion.token
import com.example.bazaar.model.Product
import com.example.bazaar.model.RegisterRequest
import com.example.bazaar.model.UpdateProfileRequest
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class UpdateViewModel (val repository: Repository) : ViewModel() {
    var user: MutableLiveData<User> = MutableLiveData()

    init{
    }

    suspend fun update() {
        val request =
            UpdateProfileRequest(username = user.value!!.username, email = user.value!!.email, phone_number = user.value!!.phone_number)
        try {
            Log.d("xxx","Profile - token:  ${MyApplication.token}")
            val result = repository.update(MyApplication.token, request)
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d("xxx", "UpdateViewModel - exception: ${e.toString()}")
        }
    }
}