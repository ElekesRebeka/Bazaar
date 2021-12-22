package com.example.bazaar.api

import com.example.bazaar.model.*
import com.example.bazaar.utils.Constants
import retrofit2.http.*

interface MarketApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST(Constants.RESET_PASSWORD_URL)
    suspend fun resetPassword(@Body request: ResetPasswordRequest): ResetPasswordResponse

    @GET(Constants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header("token") token: String, @Header("limit") limit:Int = 300): ProductResponse

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST(Constants.UPDATE_PROFILE_URL)
    suspend fun updateProfile(@Header("token") token: String, @Body request: UpdateProfileRequest): UpdateProfileResponse

    @Multipart
    @POST(Constants.ADD_PRODUCT_URL)
    suspend fun addProduct(@Header("token") token: String,
                           @Part("title") title: String,
                           @Part("description") description: String,
                           @Part("price_per_unit") price_per_unit: String,
                           @Part("units") units: String,
                           @Part("is_active") is_active: Boolean,
                           @Part("amount_type") amount_type: String,
                           @Part("price_type") price_type: String,
                           @Part("rating") rating: Double,): AddProductResponse

    @GET(Constants.GET_USER_DATA_URL)
    suspend fun getUserData(@Header("username") username: String): GetUserDataResponse

    @POST(Constants.UPDATE_PRODUCT_URL)
    suspend fun updateProduct(@Header("token") token: String, @Body request: EditProductRequest): EditProductResponse

}