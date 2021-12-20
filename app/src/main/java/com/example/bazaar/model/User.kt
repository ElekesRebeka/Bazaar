package com.example.bazaar.model

import com.squareup.moshi.JsonClass

//import com.google.gson.annotations.SerializedName

data class User(var username: String="", var password: String="", var email: String="", var phone_number: String="")

@JsonClass(generateAdapter = true)
data class LoginRequest (
    var username: String,
    var password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse (
    var username: String,
    var email: String,
    var phone_number: Int,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class RegisterRequest (
    var username: String,
    var password: String,
    var email: String,
    var phone_number: String
)

@JsonClass(generateAdapter = true)
data class RegisterResponse (
    var username: String,
    var email: String,
    var phone_number: Int,
    var token: String,
    var creation_time: Long,
    var refresh_time: Long
)

@JsonClass(generateAdapter = true)
data class UpdateProfileRequest (
    var username: String,
    var email: String,
    var phone_number: String
)

@JsonClass(generateAdapter = true)
data class UpdateData (
    var username: String,
    var phone_number: String,
    var email: String,
    var isActivated: Boolean,
    var creation_time: Long,
    var token: String,
)

@JsonClass(generateAdapter = true)
data class UpdateProfileResponse (
    var code: String,
    var updateData: UpdateData,
    var timestamp: String
)

@JsonClass(generateAdapter = true)
data class ResetPasswordRequest (
    var username: String,
    var email: String
)

@JsonClass(generateAdapter = true)
data class ResetPasswordResponse (
    var code: String,
    var message: String,
    var timestamp: String
)

@JsonClass(generateAdapter = true)
data class ResponseUser (
    var username: String,
    var phone_number: Int,
    var email: String,
    var is_activated: Boolean,
    var creation_time: Long
)

@JsonClass(generateAdapter = true)
data class GetUserDataResponse(
    var code: String,
    var data: ResponseUser,
    var timestamp: String
)



// GSon converter
//data class LoginRequest (
//    @SerializedName("username")
//    var username: String,
//
//    @SerializedName("password")
//    var password: String
//)
//
//
//data class LoginResponse (
//    @SerializedName("username")
//    var username: String,
//
//    @SerializedName("email")
//    var email: String,
//
//    @SerializedName("phone_number")
//    var phone_number: Int,
//
//    @SerializedName("token")
//    var token: String,
//
//    @SerializedName("creation_time")
//    var creation_time: Long,
//
//    @SerializedName("refresh_time")
//    var refresh_time: Long
//)