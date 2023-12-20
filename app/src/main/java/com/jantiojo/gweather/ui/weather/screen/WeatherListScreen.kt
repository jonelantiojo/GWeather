package com.jantiojo.gweather.ui.weather.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jantiojo.gweather.ui.theme.GWeatherTheme
import com.jantiojo.gweather.ui.weather.screen.component.WeatherItemComponent
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUIModelPreviewProvider
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel
import com.jantiojo.gweather.ui.weather.viewmodel.WeatherListViewModel

@Composable
fun WeatherListScreen(viewModel: WeatherListViewModel = hiltViewModel()) {
    val weathers by viewModel.allWeatherViewState.collectAsStateWithLifecycle()
    WeatherListContent(weathers = weathers)
}

@Composable
private fun WeatherListContent(
    modifier: Modifier = Modifier,
    weathers: List<CurrentWeatherUiModel>
) {
    LazyColumn(modifier = modifier) {
        items(weathers) { weather ->
            WeatherItemComponent(weatherUiModel = weather)
        }
    }
}

@Preview
@Composable
private fun WeatherListScreenPreview() {
    GWeatherTheme {
        WeatherListContent(weathers = CurrentWeatherUIModelPreviewProvider().values.toList())
    }
}