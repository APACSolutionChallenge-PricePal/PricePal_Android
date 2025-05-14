package com.example.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeApp() {
    MaterialTheme {
        Surface(modifier = Modifier) {
            HomeScreen(viewModel = HomeViewModel())
        }
    }
}