package com.example.bazaar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bazaar.MainActivity
import com.example.bazaar.model.ResponseUser
import com.example.bazaar.repository.Repository
import kotlinx.coroutines.launch

class GetUserDataViewModel (val repository: Repository) : ViewModel() {
    var user: MutableLiveData<ResponseUser> = MutableLiveData()

    init{
        val seller = MainActivity.sharedPreferences.getStringValue("seller","")
        if (seller != null) {
            getData(seller)
        }
    }

    private fun getData(username: String){
        viewModelScope.launch{
            try{
                val result = repository.getUserData(username)
                //Log.d("xxx","getData: ${result.data[0]}")
                user.value = result.data[0]
            }
            catch (e: Exception) {
                Log.d("xxx", "UpdateViewModel - exception: ${e.toString()}")
            }
        }
    }
}

