package com.example.taxi.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
suspend fun getMyLocation(context: Context): LatLng {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val location = fusedLocationClient.lastLocation.await()
    return LatLng(location.latitude, location.longitude)
}