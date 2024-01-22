package com.jantiojo.gweather.data.repository

import com.jantiojo.gweather.data.datasource.UserCredentialLocalDataSource
import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.data.entity.UserCredentialEntity
import com.jantiojo.gweather.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserCredentialRepository @Inject constructor(
    private val localDataSource: UserCredentialLocalDataSource,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun saveCredential(username: String, password: String): Flow<Boolean> {
        return flow {
            val isSuccessful = localDataSource.insertUserCredential(
                UserCredentialEntity(
                    username = username,
                    password = password
                )
            )
            emit(isSuccessful)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllUserCredentials(): Flow<List<UserCredentialEntity>> {
        return flow {
            emit(localDataSource.getAllCredentials())
        }.flowOn(Dispatchers.IO)
    }
}