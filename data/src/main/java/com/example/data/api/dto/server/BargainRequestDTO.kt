package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BargainRequestDTO(
    @Json(name = "travelCountry") val travelCountry: String
)