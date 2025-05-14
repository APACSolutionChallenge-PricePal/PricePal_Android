package com.example.taxi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

@Composable
fun TaxiApp(viewModel: TaxiViewModel) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TaxiScreen()
        }
    }
}