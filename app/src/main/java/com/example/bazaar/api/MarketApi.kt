package com.example.bazaar.api

import com.example.bazaar.utils.Constants
import com.example.bazaar.model.LoginRequest
import com.example.bazaar.model.LoginResponse
import com.example.bazaar.model.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String): ProductResponse
}