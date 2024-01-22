package com.jantiojo.gweather.data.datasource

import com.jantiojo.gweather.data.entity.UserCredentialEntity
import com.jantiojo.gweather.data.local.room.UserCredentialDao
import javax.inject.Inject

class UserCredentialLocalDataSource @Inject constructor(
    private val userCredentialDao: UserCredentialDao
) {

    suspend fun getAllCredentials(): List<UserCredentialEntity> {
        return userCredentialDao.getUserCredential()
    }

    suspend fun insertUserCredential(userCredential: UserCredentialEntity): Boolean {
        return userCredentialDao.insertUserCredential(userCredential) > 0
    }
}