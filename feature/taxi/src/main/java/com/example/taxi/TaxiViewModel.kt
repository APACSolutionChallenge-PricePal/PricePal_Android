package com.example.taxi

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaxiViewModel @Inject constructor() : ViewModel() {

    // 기본 위치 (서울)
    val defaultLocation = LatLng(37.5665, 126.9780)
}