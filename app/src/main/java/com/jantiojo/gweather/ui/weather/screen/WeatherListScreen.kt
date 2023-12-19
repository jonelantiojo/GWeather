package com.jantiojo.gweather.ui.weather.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jantiojo.gweather.ui.theme.GWeatherTheme
import com.jantiojo.gweather.ui.weather.screen.component.WeatherItemComponent

@Composable
fun WeatherListScreen() {
    LazyColumn {
        items(count = 6) {
            WeatherItemComponent()
        }
    }
}

@Preview
@Composable
fun WeatherListScreenPreview() {
    GWeatherTheme {
        WeatherListScreen()
    }
}