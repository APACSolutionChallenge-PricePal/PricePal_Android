package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DirectionsResponse(
    @field:Json(name = "routes")
    val routes: List<Route>,

    @field:Json(name = "status")
    val status: String,

    @field:Json(name = "error_message")
    val errorMessage: String? = null
)

@JsonClass(generateAdapter = true)
data class Route(
    @field:Json(name = "overview_polyline")
    val overviewPolyline: OverviewPolyline,
    @field:Json(name = "legs")
    val legs: List<Leg>
)

@JsonClass(generateAdapter = true)
data class OverviewPolyline(
    @field:Json(name = "points")
    val points: String
)

@JsonClass(generateAdapter = true)
data class Leg(
    @field:Json(name = "distance")
    val distance: Distance,
    @field:Json(name = "duration")
    val duration: Duration
)

@JsonClass(generateAdapter = true)
data class Distance(
    @field:Json(name = "value")
    val value: Int // in meters
)

@JsonClass(generateAdapter = true)
data class Duration(
    @field:Json(name = "value")
    val value: Int // in seconds
)