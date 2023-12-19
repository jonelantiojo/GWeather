package com.jantiojo.gweather.data.network.mapper

import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.data.network.dto.response.CurrentWeatherResponse

fun CurrentWeatherResponse.toCurrentWeatherEntity() = CurrentWeatherEntity(
    id = (weather?.firstOrNull()?.id ?: 0).toLong(),
    latitude = coordinate.latitude,
    longitude = coordinate.longitude,
    weatherName = weather?.firstOrNull()?.name.orEmpty(),
    weatherDescription = weather?.firstOrNull()?.description.orEmpty(),
    weatherTemperature = main.temperature,
    weatherIcon = weather?.firstOrNull()?.icon.orEmpty(),
    currentDateUtc = dateAndTime,
    countryName = systemData.country,
    cityName = city,
    sunRiseUtc = systemData.sunrise,
    sunSetUtc = systemData.sunset
)
