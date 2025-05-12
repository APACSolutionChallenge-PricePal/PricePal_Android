package com.example.pricepal

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pricepal.component.NavigationBar
import com.example.pricepal.component.NavigationItem

data class NavigationBarProp(
    val currentNavigationItem: NavigationItem?,
    val onNavigate: (NavigationItem) -> Unit,
)

@Composable
fun MainScreen(
    navigationBarProp: NavigationBarProp?,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    var recordOptionPickerHeight by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                content()
            }

            // ✅ Bottom NavigationBar
            if (navigationBarProp != null) {
                NavigationBar(
                    currentNavigationItem = navigationBarProp.currentNavigationItem,
                    onNavigate = navigationBarProp.onNavigate
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNavigationBarScreen() {
    var currentNavigationItem by remember { mutableStateOf(NavigationItem.HOME) }

    MainScreen(
        navigationBarProp = NavigationBarProp(
            currentNavigationItem = currentNavigationItem,
            onNavigate = { currentNavigationItem = it },
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "테스트")
        }
    }
}