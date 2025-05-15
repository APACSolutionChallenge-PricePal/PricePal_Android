package com.example.taxi

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Marker
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.MarkerState
import androidx.compose.material3.TextFieldDefaults

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
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

        // 내비게이션 바 색상
        systemUiController.setNavigationBarColor(Color.White)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🗺 Google Map 배경
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(state = MarkerState(position = seoul), title = "Seoul")
        }

        // 🔍 입력창 Overlay
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 40.dp)
                .shadow(
                    elevation = 11.dp,
                    shape = RoundedCornerShape(20.dp),
                    ambientColor = mainColor,
                    spotColor = mainColor
                )
                .background(Color.White.copy(alpha = 0.80f), RoundedCornerShape(20.dp))
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .drawWithContent {
                        drawContent()
                        drawLine(
                            color = highlight,
                            start = androidx.compose.ui.geometry.Offset(0f, size.height / 2),
                            end = androidx.compose.ui.geometry.Offset(size.width, size.height / 2),
                            strokeWidth = 1.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))
                        )
                    }
            ) {
                TransparentSearchInput("Please enter your departure location.")
                Spacer(modifier = Modifier.height(22.dp))
                TransparentSearchInput("Please enter your destination.")
            }

            Spacer(modifier = Modifier.width(18.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_search_green),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
            )
        }



    }
}
@Composable
fun TransparentSearchInput(hint: String) {
    var input by remember { mutableStateOf("") }

    BasicTextField(
        value = input,
        onValueChange = { input = it },
        singleLine = true,
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (input.isEmpty()) {
                    Text(
                        text = hint,
                        color = Color(0xFF4B4B4B),
                        fontSize = 15.sp,
                        maxLines = 1
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTaxiScreen() {
    TaxiScreen()
}