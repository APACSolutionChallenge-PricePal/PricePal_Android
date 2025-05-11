package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeRequestDTO(
    @Json(name = "base") val base: String,
    @Json(name = "target") val target: String
)