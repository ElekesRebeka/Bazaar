package com.example.bazaar

import android.app.Application
import com.example.bazaar.manager.SharedPreferencesManager

class MyApplication: Application(){
    companion object {
        var token: String =""
    }

}