package com.example.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.search.screen.SearchResultScreen
import com.example.search.screen.SearchScreen

@Composable
fun SearchApp(startCountry: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "placeholder") {
        // 시작 시 dummy composable (네비게이션 안정화용)
        composable("placeholder") {
            LaunchedEffect(Unit) {
                navController.navigate("search/$startCountry") {
                    popUpTo("placeholder") { inclusive = true }
                }
            }
        }

        composable(
            route = "search/{country}",
            arguments = listOf(navArgument("country") { type = NavType.StringType })
        ) { backStackEntry ->
            val country = backStackEntry.arguments?.getString("country") ?: ""
            val viewModel: SearchViewModel = hiltViewModel()
            viewModel.updateSearchQuery(country)
            SearchScreen(viewModel = viewModel, navController = navController)
        }

        composable("result") {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchResultScreen(viewModel = viewModel)
        }
    }
}
