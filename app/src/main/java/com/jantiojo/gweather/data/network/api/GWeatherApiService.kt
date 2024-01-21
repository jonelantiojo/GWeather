package com.jantiojo.gweather.data.network.api

import com.jantiojo.gweather.BuildConfig
import com.jantiojo.gweather.data.entity.TemperatureUnit
import com.jantiojo.gweather.data.network.dto.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GWeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = BuildConfig.WEATHER_KEY,
        @Query("units") tempUnits: String = TemperatureUnit.Celsius.value,
    ): CurrentWeatherResponse
}