package com.jantiojo.gweather.ui.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.GWeatherRepository
import com.jantiojo.gweather.ui.weather.screen.mapper.toCurrentWeatherUiModelList
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val repository: GWeatherRepository
) : ViewModel() {

    private val _allWeatherViewState by lazy {
        MutableStateFlow<List<CurrentWeatherUiModel>>(
            emptyList()
        )
    }
    val allWeatherViewState = _allWeatherViewState.asStateFlow()

    init {
        fetchAllWeather()
    }
    private fun fetchAllWeather() = viewModelScope.launch {
        repository.getAllWeather().collectLatest {
            Timber.d("fetch ALl Weather == $it")
            _allWeatherViewState.value = it.toCurrentWeatherUiModelList()
        }
    }
}
