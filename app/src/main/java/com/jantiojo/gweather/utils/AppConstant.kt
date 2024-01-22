package com.jantiojo.gweather.utils

object AppConstant {
    const val BASE_URL = "https://api.openweathermap.org/"

    fun getWeatherIconUrl(icon:String) : String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }
}