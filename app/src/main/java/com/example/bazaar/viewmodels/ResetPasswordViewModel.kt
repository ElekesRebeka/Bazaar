package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bazaar.MyApplication
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.model.ResetPasswordRequest
import com.example.bazaar.model.User
import com.example.bazaar.repository.Repository

class ResetPasswordViewModel (val repository: Repository) : ViewModel() {
    var user = MutableLiveData<User>()

    init {
        user.value = User()
    }

    suspend fun resetPassword() {
        val request =
            ResetPasswordRequest(username = user.value!!.username, email = user.value!!.email)
        try {
            val result = repository.resetPassword(request)
        } catch (e: Exception) {
            Log.d("xxx", "ResetPasswordViewModel - exception: ${e.toString()}")
        }
    }
}