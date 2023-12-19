package com.jantiojo.gweather.data.entity

data class CurrentWeatherEntity(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val weatherName: String,
    val weatherDescription: String,
    val weatherTemperature: Double,
    val weatherIcon: String,
    val currentDateUtc: Long,
    val countryName: String,
    val sunRiseUtc: Long,
    val sunSetUtc: Long,
    val cityName: String
)
