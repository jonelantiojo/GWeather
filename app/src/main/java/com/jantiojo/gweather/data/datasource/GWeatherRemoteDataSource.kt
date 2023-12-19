package com.jantiojo.gweather.data.datasource

import com.jantiojo.gweather.data.api.GWeatherApiService
import javax.inject.Inject

class GWeatherRemoteDataSource @Inject constructor(
    private val apiService: GWeatherApiService
) {
}