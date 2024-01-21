package com.jantiojo.gweather.data.network.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    @SerialName("coord") val coordinate: CoordinateResponse,
    val weather: List<WeatherResponse>?,
    val main: MainResponse,
    @SerialName("dt") val dateAndTime: Long,
    @SerialName("sys") val systemData: SystemResponse,
    val timezone: Long,
    val id: Long,
    @SerialName("name") val city: String,
)
