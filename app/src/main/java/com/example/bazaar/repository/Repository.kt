package com.example.bazaar.repository

import com.example.bazaar.api.RetrofitInstance
import com.example.bazaar.model.*
import retrofit2.http.Body
import retrofit2.http.Headers

class Repository {
    suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    suspend fun resetPassword(@Body request: ResetPasswordRequest): ResetPasswordResponse{
        return RetrofitInstance.api.resetPassword(request)
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return RetrofitInstance.api.register(request)
    }

    suspend fun update(token:String, request: UpdateProfileRequest): UpdateProfileResponse {
        return RetrofitInstance.api.updateProfile(token, request)
    }

    suspend fun getProducts(token: String): ProductResponse {
        return RetrofitInstance.api.getProducts(token)
    }

    suspend fun addProduct(token: String, title:String, description:String, price_per_unit: String, units: String, is_activated:Boolean, amount_type: String, price_type:String, rating:Double) :AddProductResponse{
        return RetrofitInstance.api.addProduct(token,title, description, price_per_unit, units, is_activated, amount_type, price_type, rating)
    }

    suspend fun getUserData(username: String): GetUserDataResponse {
        return RetrofitInstance.api.getUserData(username)
    }

    suspend fun getOrders(token: String): OrderResponse {
        return RetrofitInstance.api.getOrders(token)
    }
}