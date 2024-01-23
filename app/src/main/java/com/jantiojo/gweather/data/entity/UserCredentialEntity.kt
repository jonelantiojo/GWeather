package com.jantiojo.gweather.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_credential")
data class UserCredentialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String
)
