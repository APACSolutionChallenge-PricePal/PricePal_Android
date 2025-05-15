package com.example.start

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.core.MainViewModel
import com.example.core.model.Country
import com.example.start.navigation.StartNavHost

@Composable
fun StartApp(
    viewModel: StartViewModel,
    mainViewModel: MainViewModel,
    onNavigateHome: () -> Unit
) {
    StartScreen(
        viewModel = viewModel,
        onGetStartedClick = { ownCountry, travelCountry ->
            mainViewModel.setCountries(ownCountry, travelCountry)
            onNavigateHome()
        }
    )
}