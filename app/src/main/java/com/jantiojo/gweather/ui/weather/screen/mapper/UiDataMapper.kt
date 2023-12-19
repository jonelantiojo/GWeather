package com.jantiojo.gweather.ui.weather.screen.mapper

import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel
import com.jantiojo.gweather.utils.AppConstant
import com.jantiojo.gweather.utils.extensions.formatTo

fun CurrentWeatherEntity.toCurrentWeatherUiModel() = CurrentWeatherUiModel(
    weatherName = weatherName,
    weatherDescription = weatherDescription,
    weatherTemperature = "$weatherTemperature°C",
    weatherIcon = AppConstant.getWeatherIconUrl(icon = weatherIcon),
    currentDateAndTime = currentDateUtc.formatTo("EEEE MMM dd | hh:mm a"),
    locationName = "$cityName, $countryName",
    sunRiseTime = sunRiseUtc.formatTo("h:mm a"),
    sunSetTime = sunSetUtc.formatTo("h:mm a")
)