package com.example.taxi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


@Composable
fun TaxiApp() {
    val viewModel = remember { TaxiViewModel() }

    TaxiScreen(viewModel = viewModel)
}