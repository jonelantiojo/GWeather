package com.jantiojo.gweather.data.repository

import com.jantiojo.gweather.data.datasource.GWeatherRemoteDataSource
import javax.inject.Inject

class GWeatherRepository @Inject constructor(
    private val remoteDataSource: GWeatherRemoteDataSource
) {
}