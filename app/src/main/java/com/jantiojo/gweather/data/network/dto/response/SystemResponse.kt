package com.jantiojo.gweather.data.network.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SystemResponse(
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)
