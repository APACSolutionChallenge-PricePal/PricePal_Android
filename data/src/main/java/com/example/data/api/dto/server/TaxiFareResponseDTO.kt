package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaxiFareResponseDTO(
    @Json(name = "basicFare") val basicFare: String,
    @Json(name = "estimatedFare") val estimatedFare: String
)