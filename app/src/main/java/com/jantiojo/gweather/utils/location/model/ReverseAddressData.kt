package com.jantiojo.gweather.utils.location.model

import kotlinx.serialization.Serializable

@Serializable
data class ReverseAddressData(
    val streetNumber: String,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val zipcode: String,
    val latitude: Double,
    val longitude: Double,
)
