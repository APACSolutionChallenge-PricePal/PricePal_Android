package com.example.taxi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Marker
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.MarkerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.widget.Autocomplete
import com.google.maps.android.compose.MapProperties
import androidx.compose.foundation.clickable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.places.api.model.Place
import com.google.maps.android.compose.Polyline
import kotlinx.coroutines.launch


val backgroundColor = Color(0xFFFCFAF4)
val mainColor = Color(0xFF4CAE5E)
val titleBlack = Color(0xFF2A2E37)
val highlight = Color(0xFF00611A)

@SuppressLint("MissingPermission")
@Composable
fun TaxiScreen(viewModel: TaxiViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val cameraPositionState = rememberCameraPositionState()
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }

    // 상태 저장
    var startLatLng by remember { mutableStateOf<LatLng?>(null) }
    var endLatLng by remember { mutableStateOf<LatLng?>(null) }
    var isSelectingStart by remember { mutableStateOf(true) }
    var startText by remember { mutableStateOf("") }
    var endText by remember { mutableStateOf("") }

    // 1️⃣ Autocomplete Intent Launcher
    val autocompleteLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == android.app.Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(result.data!!)
                val latLng = place.latLng
                val name = place.name

                latLng?.let {
                    if (isSelectingStart) {
                        startLatLng = it
                        startText = name ?: ""
                    } else {
                        endLatLng = it
                        endText = name ?: ""
                    }
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                }
            }
        }
    )

    // 위치 권한 요청 런처
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        val latLng = LatLng(it.latitude, it.longitude)
                        currentLocation = latLng
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                    }
                }
            }
        }
    )

    val polylinePoints by viewModel.polylinePoints.collectAsState()
    val distanceMeters by viewModel.distanceMeters.collectAsState()
    val durationSeconds by viewModel.durationSeconds.collectAsState()

    val taxiFare by viewModel.taxiFare.collectAsState()
    var showFareCard by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

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

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = currentLocation != null)
        ) {
            startLatLng?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Start",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }
            endLatLng?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Destination",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                )
            }
            // ✅ Polyline 표시
            Log.d("ROUTE", "Polyline: $polylinePoints")
            if (polylinePoints.isNotEmpty()) {
                Polyline(points = polylinePoints, color = Color.Green, width = 8f)
            }
        }
        Column(){
            // 🔍 검색 입력창
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 40.dp)
                    .shadow(11.dp, RoundedCornerShape(20.dp), ambientColor = mainColor, spotColor = mainColor)
                    .background(Color.White.copy(alpha = 0.80f), RoundedCornerShape(20.dp))
                    .padding(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ClickableSearchField(
                        text = startText.ifEmpty { "Please enter your departure location." }) {
                        isSelectingStart = true
                        launchAutocomplete(context, autocompleteLauncher)
                    }
                    Spacer(modifier = Modifier.height(11.dp))
                    // ✅ 선 추가 (중간에 점선)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .drawWithContent {
                                val dashEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))
                                drawLine(
                                    color = highlight,
                                    start = Offset(0f, 0f),
                                    end = Offset(size.width, 0f),
                                    strokeWidth = 1.dp.toPx(),
                                    pathEffect = dashEffect
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                    ClickableSearchField(text = endText.ifEmpty { "Please enter your destination." }) {
                        isSelectingStart = false
                        launchAutocomplete(context, autocompleteLauncher)
                    }
                }

                val country by viewModel.countryCode.collectAsState()

//                LaunchedEffect(startLatLng, endLatLng, country) {
//                    if (startLatLng != null && endLatLng != null && country != null) {
//                        Log.d("DEBUG", "🚀 위치 또는 국가 변경 감지 → API 호출 시작")
//                        viewModel.loadRouteAndFare(startLatLng!!, endLatLng!!, country!!)
//                        showFareCard = true
//                    }
//                }

                // 검색 이미지
                Spacer(modifier = Modifier.width(18.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_search_green),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            if (startLatLng != null && endLatLng != null) {
                                // 1️⃣ 국가 설정 후 콜백에서 처리
                                viewModel.setCountryFromLocation(context, startLatLng!!) { country ->
                                    if (country != null) {
                                        // 2️⃣ 카메라 이동
                                        val bounds = LatLngBounds.builder()
                                            .include(startLatLng!!)
                                            .include(endLatLng!!)
                                            .build()

                                        coroutineScope.launch {
                                            cameraPositionState.animate(
                                                update = CameraUpdateFactory.newLatLngBounds(bounds, 100)
                                            )
                                        }

                                        // 3️⃣ 택시 요금 API 호출
                                        viewModel.loadRouteAndFare(
                                            start = startLatLng!!,
                                            end = endLatLng!!,
                                            country = country,
                                            context = context,
                                            onFail = {
                                                Toast.makeText(context, "유효한 경로를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                                                showFareCard = false
                                            },
                                            onSuccess = {
                                                showFareCard = true
                                            }
                                        )
                                    } else {
                                        Toast.makeText(context, "국가 정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                )
            }
        }
        if (showFareCard && taxiFare != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text("🚕 The Basic Taxi Fares", fontSize = 18.sp, color = titleBlack)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Basic Fare: ${taxiFare!!.basicFare}", fontSize = 16.sp)
                    Text("Distance: ${taxiFare!!.distance} km", fontSize = 16.sp)
                    Text("Estimated Fare: ${taxiFare!!.estimatedFare}", fontSize = 16.sp)
                }
            }
        }
    }
}

// ✨ 클릭 가능한 검색창 Composable
@Composable
fun ClickableSearchField(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = if (text.startsWith("Please")) Color(0xFF4B4B4B) else Color.Black
        )
    }
}

// ✨ Autocomplete Intent 실행 함수
fun launchAutocomplete(context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
        .build(context)
    launcher.launch(intent)
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewTaxiScreen() {
//    TaxiScreen()
//}