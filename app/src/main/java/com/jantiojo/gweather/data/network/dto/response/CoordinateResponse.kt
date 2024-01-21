package com.jantiojo.gweather.data.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinateResponse(
    @SerialName("lon") val longitude: Double,
    @SerialName("lat") val latitude: Double,
)
