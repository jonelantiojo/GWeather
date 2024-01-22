package com.jantiojo.gweather.data.repository

import com.jantiojo.gweather.data.datasource.RouteDestinationLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainScreenRepository @Inject constructor(
    private val routeDestinationLocalDataSource: RouteDestinationLocalDataSource
) {

    suspend fun saveRouteStartDestination(route: String) {
        routeDestinationLocalDataSource.setStartRouteDestination(route)
    }

    suspend fun routeDestination(): Flow<String> {
        return flow {
            emit(routeDestinationLocalDataSource.startRouteDestination())
        }.flowOn(Dispatchers.IO)
    }

}