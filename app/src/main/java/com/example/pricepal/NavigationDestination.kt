package com.example.pricepal

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class NavigationDestination(val route: String) {

    fun NavGraphBuilder.setNavGraph(
        content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    ) {
        composable(route = this@NavigationDestination.route, content = content)
    }

    data object Splash : NavigationDestination("splash")
    data object Start : NavigationDestination("start")
    data object Home : NavigationDestination("home") // 탭 네비게이션 진입점
    data object Search : NavigationDestination("search")
    data object Taxi : NavigationDestination("taxi")
}
