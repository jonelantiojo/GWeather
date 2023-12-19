package com.jantiojo.gweather.ui.weather.screen.model

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jantiojo.gweather.utils.AppConstant

class CurrentWeatherUIModelPreviewProvider : PreviewParameterProvider<CurrentWeatherUiModel> {
    override val values: Sequence<CurrentWeatherUiModel>
        get() = sequenceOf(
            CurrentWeatherUiModel(
                weatherName = "Clear Sky",
                weatherDescription = "Very Clear Sky",
                weatherTemperature = "30°C",
                weatherIcon = AppConstant.getWeatherIconUrl(icon = "01d"),
                currentDateAndTime = "Wednesday Dec 19 | 07:40 AM",
                locationName = "Manila, PH",
                sunRiseTime = "6:40 AM",
                sunSetTime = "5:50 PM"
            )
        )
}