package com.jantiojo.gweather.data.datasource

import com.jantiojo.gweather.data.local.RouteDestinationDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RouteDestinationLocalDataSource @Inject constructor(
    private val routeDestinationDataStore: RouteDestinationDataStore
) {
    
    suspend fun setStartRouteDestination(route: String) {
        routeDestinationDataStore.setStartRouteDestination(route)
    }

    suspend fun startRouteDestination(): String {
        return routeDestinationDataStore.startRouteDestination.first()
    }
}