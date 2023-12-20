package com.jantiojo.gweather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val weatherName: String,
    val weatherDescription: String,
    val weatherTemperature: Double,
    val weatherIcon: String,
    val currentDateUtc: Long,
    val countryName: String,
    val sunRiseUtc: Long,
    val sunSetUtc: Long,
    val cityName: String
)
