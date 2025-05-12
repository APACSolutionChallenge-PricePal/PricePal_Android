package com.example.taxi

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.taxi.location.getMyLocation
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun TaxiScreen(viewModel: TaxiViewModel) {
    val context = LocalContext.current
    val latitude by viewModel.latitude.collectAsState()
    val longitude by viewModel.longitude.collectAsState()

    val cameraPositionState = rememberCameraPositionState()
    val mapHandler = remember { MapHandler(cameraPositionState) }

    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(Unit) {
        userLocation = getMyLocation(context)
        userLocation?.let {
            mapHandler.moveToLocation(it)
        }
    }

    val uiSettings = remember {
        MapUiSettings(myLocationButtonEnabled = true)
    }
    val properties = remember {
        MapProperties(isMyLocationEnabled = true)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        if (latitude != 0.0 && longitude != 0.0) {
            Marker(
                state = MarkerState(position = LatLng(latitude, longitude))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaxiScreenPreview() {
    val fakeViewModel = remember {
        object : TaxiViewModel() {
            override val latitude = MutableStateFlow(37.56)
            override val longitude = MutableStateFlow(126.97)
        }
    }

    TaxiScreen(viewModel = fakeViewModel)
}