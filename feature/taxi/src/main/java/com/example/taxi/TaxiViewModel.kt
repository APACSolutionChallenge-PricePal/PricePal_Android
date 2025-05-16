package com.example.taxi

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.TaxiFare
import com.example.core.repository.DirectionsRepository
import com.example.core.repository.TaxiFareRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TaxiViewModel @Inject constructor(
    private val directionsRepository: DirectionsRepository,
    private val TaxiFareRepository: TaxiFareRepository
) : ViewModel() {

    private val _polylinePoints = MutableStateFlow<List<LatLng>>(emptyList())
    val polylinePoints: StateFlow<List<LatLng>> = _polylinePoints

    private val _distanceMeters = MutableStateFlow(0)
    val distanceMeters: StateFlow<Int> = _distanceMeters

    private val _durationSeconds = MutableStateFlow(0)
    val durationSeconds: StateFlow<Int> = _durationSeconds

    private val _taxiFare = MutableStateFlow<TaxiFare?>(null)
    val taxiFare: StateFlow<TaxiFare?> = _taxiFare

    fun loadRouteAndFare(start: LatLng, end: LatLng, country: String) {
        viewModelScope.launch {
            val response = directionsRepository.getRoute(start, end)
            _polylinePoints.value = response?.polylinePoints ?: emptyList()
            _distanceMeters.value = response?.distanceMeters ?: 0
            _durationSeconds.value = response?.durationSeconds ?: 0

            val distanceKm = _distanceMeters.value / 1000.0

            val fareResult = TaxiFareRepository.getTaxiFare(distanceKm.toString(), country.lowercase())
            _taxiFare.value = fareResult
            Log.d("DEBUG", "Fare 요청 - country: $country, distance: $distanceKm")

        }
    }

    // TaxiViewModel.kt
    private val _countryCode = MutableStateFlow<String?>(null)
    val countryCode: StateFlow<String?> = _countryCode

    fun setCountryFromLocation(context: Context, latLng: LatLng) {
        val geocoder = Geocoder(context, Locale.ENGLISH) // ← 이 부분 고정!
        val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        val country = addresses?.firstOrNull()?.countryName?.lowercase(Locale.ENGLISH)
        _countryCode.value = country
    }


    fun loadRoute(start: LatLng, end: LatLng) {
        viewModelScope.launch {
            val response = directionsRepository.getRoute(start, end)
            Log.d("DEBUG", "API 전체 응답: $response")
            Log.d("DEBUG", "Polyline: ${response?.polylinePoints}")
            _polylinePoints.value = response?.polylinePoints ?: emptyList()
            _distanceMeters.value = response?.distanceMeters ?: 0
            _durationSeconds.value = response?.durationSeconds ?: 0

        }
    }
}