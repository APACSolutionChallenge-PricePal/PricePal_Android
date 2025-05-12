package com.example.start.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.start.StartScreen
import com.example.start.StartViewModel

@Composable
fun StartNavHost(
    navController: NavHostController,
    viewModel: StartViewModel,
) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(
                viewModel = viewModel,
                onGetStartedClick = { ownCountry, travelCountry ->
                    navController.navigate("home/${ownCountry}/${travelCountry}")
                }
            )
        }
    }
}