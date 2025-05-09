package com.example.search

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.search.screen.SearchResultScreen
import com.example.search.screen.SearchScreen

@Composable
fun SearchApp(viewModel: SearchViewModel = SearchViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(viewModel = viewModel, navController = navController)
        }
        composable("result") {
            SearchResultScreen(viewModel = viewModel)
        }
    }
}