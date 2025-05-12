package com.example.pricepal

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.home.HomeApp
import com.example.start.StartApp
import com.example.pricepal.component.NavigationBar
import com.example.pricepal.component.NavigationItem
import com.example.pricepal.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun MainApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route
    var showNavBar by remember { mutableStateOf(false) }

    val showBottomBar = when (currentRoute) {
        NavigationItem.HOME.toString(),
        NavigationItem.MAP.toString(),
        NavigationItem.SEARCH.toString() -> true
        else -> false
    }

    var showSplash by remember { mutableStateOf(true) }
    var currentNavigationItem by remember { mutableStateOf<NavigationItem?>(null) }

    if (showSplash) {
        SplashScreen {
            showSplash = false
            navController.navigate(NavigationItem.HOME) {
                popUpTo(0)
            }
        }
    } else {
        MainScreen(
            navigationBarProp = if (showBottomBar) NavigationBarProp(
                currentNavigationItem = currentNavigationItem,
                onNavigate = {
                    if (it != currentNavigationItem) {
                        currentNavigationItem = it
                        navController.navigate(it) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
            ) else null
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationDestination.Splash,
                modifier = Modifier.fillMaxSize()
            ) {
                with(NavigationDestination.Splash) {
                    setNavGraph {
                        LaunchedEffect(Unit) { showNavBar = false }

                        SplashScreen{}
                    }
                }

                with(NavigationDestination.Home) {
                    setNavGraph {
                        LaunchedEffect(Unit) { showNavBar = false }
                        FinishHandler()

                        StartApp(
                            viewModel = hiltViewModel()
                        )
                    }
                }

                with(NavigationDestination.Start) {
                    setNavGraph {
                        LaunchedEffect(Unit) { showNavBar = false }
                        FinishHandler()

                        StartApp(
                            viewModel = hiltViewModel()
                        )
                    }
                }

                with(NavigationDestination.Home) {
                    setNavGraph {
                        //val homeViewModel = hiltViewModel()
                        LaunchedEffect(Unit) {
                            showNavBar = false
                            //homeViewModel.fetchData()
                        }
                        FinishHandler()

                        Column {
                            Spacer(
                                modifier = Modifier.height(
                                    WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
                                )
                            )
                            HomeApp()
                        }
                    }
                }

                with(NavigationDestination.Search) {
                    setNavGraph {
                        LaunchedEffect(Unit) { showNavBar = false }
                        FinishHandler()

                        // 추가 개발 필요
                        //SearchApp()
                    }
                }

                with(NavigationDestination.Taxi) {
                    setNavGraph {
                        LaunchedEffect(Unit) { showNavBar = false }
                        FinishHandler()

                        // 추가 개발 필요
                        //TaxiApp()
                    }
                }
            }
        }
    }
}


@Composable
private fun FinishHandler() {
    val activity = LocalActivity.current
    var backPressedTime by remember { mutableLongStateOf(0L) }

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 2000L)
            activity?.finish()
        else
            Toast.makeText(activity, "뒤로 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = currentTime
    }
}
