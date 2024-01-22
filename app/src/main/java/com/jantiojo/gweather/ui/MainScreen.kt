package com.jantiojo.gweather.ui

import AnimatedBottomTabsApp
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jantiojo.gweather.ui.onboarding.screen.LoginScreen
import com.jantiojo.gweather.ui.onboarding.screen.SignUpScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Routes.SignUp.route) {
            SignUpScreen(navController = navController)
        }

        composable(Routes.Home.route) {
            AnimatedBottomTabsApp()
        }
    }
}
