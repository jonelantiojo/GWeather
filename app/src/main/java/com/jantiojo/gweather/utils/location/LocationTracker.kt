package com.jantiojo.gweather.utils.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}
