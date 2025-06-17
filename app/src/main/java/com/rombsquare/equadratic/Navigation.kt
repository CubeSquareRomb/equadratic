package com.rombsquare.equadratic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rombsquare.equadratic.gamescreen.GameScreen
import com.rombsquare.equadratic.mainscreen.MainScreen

@Composable
fun NavApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, innerPadding)
        }

        composable(
            "game/{diff}",
            arguments = listOf(navArgument("diff") { type = NavType.StringType })
        ) { backStackEntry ->
            val diff = backStackEntry.arguments?.getString("diff")!!
            GameScreen(navController, innerPadding, diff)
        }

        composable("tutorial") {
            TutorialScreen(navController)
        }
    }
}