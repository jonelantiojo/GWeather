package com.jantiojo.gweather.ui

import AnimatedBottomTabsApp
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jantiojo.gweather.ui.onboarding.screen.LoginPage

@Composable
fun ScreenMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.SignUp.route) {
//            SignUp(navController = navController)
        }

        composable(Routes.Home.route) {
            BackHandler(true) {
                //DO NOTHING
            }
            AnimatedBottomTabsApp()
        }
    }
}