package com.example.data.api.dto.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DirectionsResponse(
    @field:Json(name = "routes")
    val routes: List<Route>
)

data class Route(
    @field:Json(name = "overview_polyline")
    val overviewPolyline: OverviewPolyline,
    @field:Json(name = "legs")
    val legs: List<Leg>
)

data class OverviewPolyline(
    @field:Json(name = "points")
    val points: String
)

data class Leg(
    @field:Json(name = "distance")
    val distance: Distance,
    @field:Json(name = "duration")
    val duration: Duration
)

data class Distance(
    @field:Json(name = "value")
    val value: Int // in meters
)

data class Duration(
    @field:Json(name = "value")
    val value: Int // in seconds
)