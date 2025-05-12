package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TipDTO(
    @Json(name = "romanization") val romanization: String,
    @Json(name = "local") val local: String,
    @Json(name = "english") val english: String
)