package com.example.data.implementation.repository

import android.util.Log
import com.example.core.repository.DirectionsRepository
import com.example.core.repository.DirectionsResult
import com.example.data.BuildConfig
import com.example.data.api.DirectionsApi
import com.example.data.api.dto.server.DirectionsResponse
import com.example.data.util.PolylineUtils.decodePolyline
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import javax.inject.Inject

class DirectionsRepositoryImpl @Inject constructor(
    private val directionsApi: DirectionsApi
) : DirectionsRepository {

    override suspend fun getRoute(start: LatLng, end: LatLng): DirectionsResult? {
        val origin = "${start.latitude},${start.longitude}"
        val destination = "${end.latitude},${end.longitude}"
        val response = directionsApi.getDirections(
            origin = origin,
            destination = destination,
            apiKey = BuildConfig.MAPS_API_KEY

        )
        Log.d("DIRECTIONS", "📌 API 호출: origin=$origin, destination=$destination")
        Log.d("DIRECTIONS", "✅ 응답 성공 여부: ${response.isSuccessful}")


        if (response.isSuccessful) {
            val body = response.body()
            Log.d("DIRECTIONS", "📦 Body: $body")
            Log.d("DIRECTIONS", "📈 Status: ${body?.status}, Error: ${body?.errorMessage}")
            Log.d("DIRECTIONS", "🛣️ Routes found: ${body?.routes?.size}")
            return body?.let { parseDirections(it) }
        } else {
            Log.e("DIRECTIONS", "❌ API 오류: ${response.errorBody()?.string()}")
            Log.d("DIRECTIONS_API", "Request: origin=$origin, destination=$destination, mode=driving")
        }
        return null
    }

    private fun parseDirections(response: DirectionsResponse): DirectionsResult? {
        val route = response.routes.firstOrNull() ?: return null
        val leg = route.legs.firstOrNull() ?: return null
        val polyline = route.overviewPolyline.points

        return DirectionsResult(
            polylinePoints = decodePolyline(polyline),
            distanceMeters = leg.distance.value,
            durationSeconds = leg.duration.value
        )
    }
}