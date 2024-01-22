package com.jantiojo.gweather.utils

sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data object Idle : UiState<Nothing>()
}

