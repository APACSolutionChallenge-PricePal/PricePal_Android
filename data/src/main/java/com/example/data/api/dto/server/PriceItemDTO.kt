package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PriceItemDTO(
    @Json(name = "itemName") val itemName: String,
    @Json(name = "userPrice") val userPrice: String,
    @Json(name = "travelPrice") val travelPrice: String,
    @Json(name = "image") val image: String
)