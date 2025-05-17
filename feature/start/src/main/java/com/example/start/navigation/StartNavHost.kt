package com.example.start.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.model.Country
import com.example.start.StartScreen
import com.example.start.StartViewModel
import com.example.home.HomeViewModel
import com.example.home.HomeScreen
import com.google.gson.Gson

@Composable
fun StartNavHost(
    navController: NavHostController,
    viewModel: StartViewModel,
) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            val gson = Gson()
            StartScreen(
                viewModel = viewModel,
                onGetStartedClick = { ownCountry, travelCountry ->
                    val encodedOwn = Uri.encode(gson.toJson(ownCountry))
                    val encodedTravel = Uri.encode(gson.toJson(travelCountry))
                    navController.navigate("home/$encodedOwn/$encodedTravel")
                }
            )
        }

        composable(
            "home/{own}/{travel}",
            arguments = listOf(
                navArgument("own") { type = NavType.StringType },
                navArgument("travel") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val gson = Gson()
            val ownJson = backStackEntry.arguments?.getString("own") ?: ""
            val travelJson = backStackEntry.arguments?.getString("travel") ?: ""

            val ownCountry = gson.fromJson(ownJson, Country::class.java)
            val travelCountry = gson.fromJson(travelJson, Country::class.java)

            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = homeViewModel,
                travelCountry = travelCountry,
                ownCountry = ownCountry
            )
        }
    }
}