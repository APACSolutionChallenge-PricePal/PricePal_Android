package com.example.start

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.start.navigation.StartNavHost

@Composable
fun StartApp(viewModel: StartViewModel) {
    val navController = rememberNavController()
    StartNavHost(navController = navController, viewModel = viewModel)
}