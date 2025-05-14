package com.example.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.model.Country

@Composable
fun HomeApp(
    viewModel: HomeViewModel,
    ownCountry: Country,
    travelCountry: Country
) {
    MaterialTheme {
        Surface(modifier = Modifier) {
            HomeScreen(
                viewModel = viewModel,
                ownCountry = ownCountry,
                travelCountry = travelCountry
            )
        }
    }
}