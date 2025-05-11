package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryItemDTO(
    @Json(name = "country_iso_alp2") val countryCode: String,
    @Json(name = "download_url") val downloadUrl: String
)