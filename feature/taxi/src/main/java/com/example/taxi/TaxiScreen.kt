package com.example.taxi

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Marker
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.MarkerState

val backgroundColor = Color(0xFFFCFAF4)
val mainColor = Color(0xFF4CAE5E)
val titleBlack = Color(0xFF2A2E37)
val highlight = Color(0xFF00611A)

@Composable
fun TaxiScreen() {
    val seoul = LatLng(37.5665, 126.9780)
    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(seoul, 12f)

    // 시스템 UI 색상 덮기
    val systemUiController = rememberSystemUiController()
    SideEffect {
        // 상태바 색상
        systemUiController.setStatusBarColor(backgroundColor)

        // 내비게이션 바 색상
        systemUiController.setNavigationBarColor(Color.White)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = seoul),
            title = "Seoul"
        )
    }
}