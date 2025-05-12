package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GuideRequestDTO(
    @Json(name = "itemName") val itemName: String,
    @Json(name = "country") val country: String
)