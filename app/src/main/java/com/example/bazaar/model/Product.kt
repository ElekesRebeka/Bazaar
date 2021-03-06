package com.example.bazaar.model

import com.squareup.moshi.JsonClass
import java.lang.reflect.Array

@JsonClass(generateAdapter = true)
data class Image(var _id: String, var image_id: String, var image_name: String, var image_path: String)

@JsonClass(generateAdapter = true)
data class Product(var rating: Double=0.0,
                   var amount_type: String="",
                   var price_type: String="",
                   var product_id: String="",
                   var username: String="",
                   var is_active: Boolean=false,
                   var price_per_unit: String="",
                   var units: String="",
                   var description: String="",
                   var title: String="",
                   var images: List<Image> = listOf(),
                   var creation_time: Long=0
)

@JsonClass(generateAdapter = true)
data class ProductResponse(val item_count: Int, val products: List<Product>, val timestamp: Long)

@JsonClass(generateAdapter = true)
data class AddProductRequest (
    var rating: Double=0.0,
    var amount_type: String="",
    var price_type: String="",
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String
)

@JsonClass(generateAdapter = true)
data class AddProductResponse (
    var creation: String,
    var product_id: String,
    var username: String,
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    var amount_type: String,
    var price_type: String,
    var images: List<Image>,
    var creation_time: Long
)

@JsonClass(generateAdapter = true)
data class EditProductRequest (
    var price_per_unit: Int,
    var is_active: Boolean,
    var title: String,
    var rating: String,
    var amount_type: String,
    var price_type: String
)

@JsonClass(generateAdapter = true)
data class EditProductResponse (
    var rating: Double,
    var amount_type: String,
    var price_type: String,
    var product_id: String,
    var username: String,
    var is_active: Boolean,
    var price_per_unit: String,
    var units: String,
    var description: String,
    var title: String,
    var images: List<Image>,
    var creation_time: Long,
    var messages: List<String>
)

