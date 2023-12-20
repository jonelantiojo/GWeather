package com.jantiojo.gweather.data.datasource

import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.data.room.WeatherDao
import javax.inject.Inject

class GWeatherLocalDataSource @Inject constructor(
    private val weatherDao: WeatherDao
) {

    suspend fun getAllWeather(): List<CurrentWeatherEntity> {
        return weatherDao.getAllWeather()
    }

    suspend fun insertWeather(weather: CurrentWeatherEntity) {
        return weatherDao.insertWeather(weather)
    }
}
