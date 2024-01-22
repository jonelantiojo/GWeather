package com.jantiojo.gweather.ui

sealed class Routes(val route: String) {

    data object Login : Routes("Login")
    data object SignUp : Routes("SignUp")
    data object Home : Routes("Home")
}