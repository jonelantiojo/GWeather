package com.jantiojo.gweather.data.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val id: Int,
    @SerialName("main") val name: String,
    val description: String,
    val icon: String
)
