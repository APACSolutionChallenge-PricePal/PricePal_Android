package com.example.taxi

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

class MapHandler(
    private val cameraPositionState: CameraPositionState
) {
    suspend fun moveToLocation(latLng: LatLng, zoom: Float = 17f) {
        val update = CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        cameraPositionState.animate(update)
    }
}