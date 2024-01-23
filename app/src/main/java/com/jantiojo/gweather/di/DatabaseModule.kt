package com.jantiojo.gweather.di

import android.content.Context
import androidx.room.Room
import com.jantiojo.gweather.data.datasource.GWeatherLocalDataSource
import com.jantiojo.gweather.data.local.room.AppDatabase
import com.jantiojo.gweather.data.local.room.UserCredentialDao
import com.jantiojo.gweather.data.local.room.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "room_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideUserCredentialDao(appDatabase: AppDatabase): UserCredentialDao {
        return appDatabase.userCredentialDao()
    }
}