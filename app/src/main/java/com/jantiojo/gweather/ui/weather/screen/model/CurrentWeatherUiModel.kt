package com.jantiojo.gweather.ui.weather.screen.model

import androidx.compose.runtime.Immutable

@Immutable
data class CurrentWeatherUiModel(
    val weatherName: String = "",
    val weatherDescription: String = "",
    val weatherTemperature: String = "",
    val weatherIcon: String = "",
    val currentDateAndTime: String = "",
    val locationName: String = "",
    val sunRiseTime: String = "",
    val sunSetTime: String = "",
    val isPastSixPm: Boolean = false
)
