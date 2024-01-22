package com.jantiojo.gweather.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jantiojo.gweather.data.entity.UserCredentialEntity

@Dao
interface UserCredentialDao {

    @Query("SELECT * FROM users_credential")
    suspend fun getUserCredential(): List<UserCredentialEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCredential(userCredential: UserCredentialEntity): Long
}