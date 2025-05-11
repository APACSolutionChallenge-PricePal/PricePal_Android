package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxiFareRequestDTO(
    @Json(name = "distance") val distance: String,
    @Json(name = "country") val country: String
)