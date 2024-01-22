package com.jantiojo.gweather.ui

import AnimatedBottomTabsApp
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jantiojo.gweather.ui.onboarding.screen.LoginScreen
import com.jantiojo.gweather.ui.onboarding.screen.SignUpScreen

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {

    val routeStartDestination = viewModel.routeStartDestinationState.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    routeStartDestination.value?.let { startDestination ->

        NavHost(navController = navController, startDestination = startDestination) {

            composable(Routes.Login.route) {
                LoginScreen(
                    navigateToSignUpScreen = {
                        navController.navigate(Routes.SignUp.route)
                    },
                    navigateToHomeScreen = {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) {
                                inclusive = true
                            }
                        }
                    })
            }

            composable(Routes.SignUp.route) {
                SignUpScreen(
                    onNavigateToLogin = {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Routes.Home.route) {
                AnimatedBottomTabsApp()
            }
        }
    }

}
