package com.example.pricepal

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import android.content.Intent
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val context = LocalContext.current

    // 상태바/내비바 색상 설정
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
    }

    // 2초 후 StartScreenApp 으로 이동
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }

    // Radial Gradient 배경 + 중앙 로고
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF4CAE5E), // 메인 컬러
                        Color(0xFF93CFA4)  // 서브 컬러
                    ),
                    center = Offset.Unspecified,
                    radius = 1000f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pricepal_logo_png),
            contentDescription = "PricePal Logo",
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    // 실제 타이머 기능은 Preview에서 필요 없으니 빈 람다 전달
    SplashScreen(onTimeout = {})
}