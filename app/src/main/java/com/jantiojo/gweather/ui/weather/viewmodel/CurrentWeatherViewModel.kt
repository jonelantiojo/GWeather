package com.jantiojo.gweather.ui.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.GWeatherRepository
import com.jantiojo.gweather.ui.weather.screen.mapper.toCurrentWeatherUiModel
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val repository: GWeatherRepository
) : ViewModel() {

    private val _currentWeatherViewState by lazy { MutableStateFlow(CurrentWeatherUiModel()) }
    val currentWeatherViewState = _currentWeatherViewState.asStateFlow()

    init {
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() = viewModelScope.launch {
        repository.getCurrentWeather().collectLatest {
            Timber.d("fetchCurrentWeather == $it")
            if (it != null) {
                _currentWeatherViewState.value = it.toCurrentWeatherUiModel()
            }
        }
    }
}