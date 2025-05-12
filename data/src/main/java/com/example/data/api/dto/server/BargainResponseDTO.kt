package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BargainResponseDTO(
    @Json(name = "tips") val tips: List<TipDTO>,
    @Json(name = "summary") val summary: String
)