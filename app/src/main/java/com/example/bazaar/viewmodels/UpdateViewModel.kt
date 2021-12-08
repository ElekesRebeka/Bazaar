package com.example.bazaar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.MyApplication
import com.example.bazaar.model.RegisterRequest
import com.example.bazaar.model.UpdateProfileRequest
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository

class UpdateViewModel (val context: Context, val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun update() {
        val request =
            UpdateProfileRequest(username = user.value!!.username, email = user.value!!.email, phone_number = user.value!!.phone_number)
        try {
            val result = repository.update(request)
            Log.d("xxx", "MyApplication - token:  ${MyApplication.token}")
        } catch (e: Exception) {
            Log.d("xxx", "UpdateViewModel - exception: ${e.toString()}")
        }
    }
}