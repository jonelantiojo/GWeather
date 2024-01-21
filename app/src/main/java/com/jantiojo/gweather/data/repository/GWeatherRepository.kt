package com.jantiojo.gweather.data.repository

import com.jantiojo.gweather.data.datasource.GWeatherRemoteDataSource
import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GWeatherRepository @Inject constructor(
    private val remoteDataSource: GWeatherRemoteDataSource,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getCurrentWeather(): Flow<CurrentWeatherEntity?> {
        return flow {
            val currentWeather =
                remoteDataSource.getCurrentWeather(lat = "14.604", long = "120.982")
            emit(currentWeather)
        }.flowOn(Dispatchers.IO)
    }
}