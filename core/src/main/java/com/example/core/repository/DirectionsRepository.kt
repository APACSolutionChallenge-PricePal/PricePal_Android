package com.example.core.repository

import com.google.android.gms.maps.model.LatLng

data class DirectionsResult(
    val polylinePoints: List<LatLng>,
    val distanceMeters: Int,
    val durationSeconds: Int
)

interface DirectionsRepository {
    suspend fun getRoute(
        start: LatLng,
        end: LatLng
    ): DirectionsResult?
}