package com.example.democompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.democompose.view.Screen
import com.example.democompose.view.main.MainScreen
import com.example.democompose.view.detail.DetailScreen

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.MainScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(
            Screen.DetailScreen.route + "/{championId}",
            arguments = listOf(
                navArgument("championId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            DetailScreen(navController, it.arguments)
        }
    }
}