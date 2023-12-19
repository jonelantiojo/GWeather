package com.jantiojo.gweather.data.datasource

import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.data.network.api.GWeatherApiService
import com.jantiojo.gweather.data.network.mapper.toCurrentWeatherEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GWeatherRemoteDataSource @Inject constructor(
    private val apiService: GWeatherApiService
) {

    suspend fun getCurrentWeather(
        lat: String, long: String
    ): CurrentWeatherEntity? {
        return runCatching {
            apiService.getCurrentWeather(
                lat = lat,
                lon = long
            ).toCurrentWeatherEntity()
        }.getOrNull()
    }
}
