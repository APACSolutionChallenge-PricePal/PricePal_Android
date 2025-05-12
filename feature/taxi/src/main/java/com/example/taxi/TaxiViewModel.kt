package com.example.taxi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class TaxiViewModel : ViewModel() {
//    private val _latitude = MutableStateFlow(37.56) // 서울 위도 예시
//    val latitude: StateFlow<Double> = _latitude
//
//    private val _longitude = MutableStateFlow(126.97) // 서울 경도 예시
//    val longitude: StateFlow<Double> = _longitude

//    // 필요 시 setter 함수도 제공 가능
//    fun setLocation(lat: Double, lng: Double) {
//        _latitude.value = lat
//        _longitude.value = lng
//    }

    open val latitude: StateFlow<Double> = MutableStateFlow(0.0)
    open val longitude: StateFlow<Double> = MutableStateFlow(0.0)

    fun setLocation(lat: Double, lng: Double) {
        (latitude as MutableStateFlow).value = lat
        (longitude as MutableStateFlow).value = lng
    }

}