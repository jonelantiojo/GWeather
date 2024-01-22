package com.jantiojo.gweather.ui.onboarding.screen.model

import androidx.compose.runtime.Immutable

@Immutable
data class LoginUiModel(
    val username: String,
    val password: String
)
