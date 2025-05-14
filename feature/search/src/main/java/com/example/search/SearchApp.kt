package com.example.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val hasInitialized = remember { mutableStateOf(false) }

    // ✅ ViewModel은 NavHost 바깥에서 단 한 번 생성하여 공유
    val viewModel: SearchViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "placeholder") {
        // 초기 진입을 위한 placeholder 화면
        composable("placeholder") {
            LaunchedEffect(Unit) {
                navController.navigate("search/$startCountry") {
                    popUpTo("placeholder") { inclusive = true }
                }
            }
        }

        // 검색 화면
        composable(
            route = "search/{country}",
            arguments = listOf(navArgument("country") { type = NavType.StringType })
        ) { backStackEntry ->
            val country = backStackEntry.arguments?.getString("country") ?: ""

            // 2. 초기 진입 시에만 country로 업데이트
            if (!hasInitialized.value) {
                viewModel.updateSearchQuery(country)
                hasInitialized.value = true
            }
            SearchScreen(viewModel = viewModel, navController = navController)
        }

        // 검색 결과 화면
        composable("result") {
            SearchResultScreen(viewModel = viewModel) // 🔁 동일 ViewModel 공유
        }
    }
}