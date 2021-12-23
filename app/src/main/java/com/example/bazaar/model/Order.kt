package com.example.bazaar.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Messages(
        var username: String,
        var message_id: String,
        var message: String,
        var user_photo: String="",
        var creation_time: Long
)

@JsonClass(generateAdapter = true)
data class Order(
        var order_id: String="",
        var username: String="",
        var status: String="",
        var owner_username: String="",
        var price_per_unit: String="",
        var units: String="",
        var description: String="",
        var title: String="",
        var images: List<Image> = listOf(),
        var creation_time: Long=0,
        var messages: List<Messages> = listOf()
    )

@JsonClass(generateAdapter = true)
data class OrderResponse(val item_count: Int, val orders: List<Order>, val timestamp: Long)