package com.example.bazaar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.MyApplication
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.model.RegisterRequest
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository

class RegisterViewModel(val context: Context, val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun register() {
        val request =
            RegisterRequest(username = user.value!!.username, password = user.value!!.password, email=user.value!!.email, phone_number = user.value!!.phone_number)
        try {
            val result = repository.register(request)
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d("xxx", "RegisterViewModel - exception: ${e.toString()}")
        }
    }
}