package com.jantiojo.gweather.data.repository

import com.jantiojo.gweather.data.datasource.GWeatherLocalDataSource
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
    private val localDataSource: GWeatherLocalDataSource,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Flow<CurrentWeatherEntity?> {
        return flow {
            val currentWeather =
                remoteDataSource.getCurrentWeather(lat = "$latitude", long = "$longitude")
            currentWeather?.let {
                localDataSource.insertWeather(it)
            }
            emit(currentWeather)
        }.flowOn(dispatcher)
    }

    suspend fun getAllWeather(): Flow<List<CurrentWeatherEntity>> {
        return flow {
            emit(localDataSource.getAllWeather())
        }.flowOn(dispatcher)
    }
}