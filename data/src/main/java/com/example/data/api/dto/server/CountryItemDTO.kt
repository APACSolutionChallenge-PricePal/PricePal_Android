package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryItemDTO(
    @Json(name = "countryEngNm") val countryName: String,
    @Json(name = "countryIsoAlp2") val countryCode: String,
    @Json(name = "downloadUrl") val downloadUrl: String
)