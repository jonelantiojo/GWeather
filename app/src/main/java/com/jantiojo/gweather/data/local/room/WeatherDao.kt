package com.jantiojo.gweather.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jantiojo.gweather.data.entity.CurrentWeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun getAllWeather() : List<CurrentWeatherEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: CurrentWeatherEntity)
}