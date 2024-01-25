package com.jantiojo.gweather.ui.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.GWeatherRepository
import com.jantiojo.gweather.ui.weather.screen.mapper.toCurrentWeatherUiModel
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel
import com.jantiojo.gweather.utils.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val repository: GWeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _currentWeatherViewState by lazy { MutableStateFlow(CurrentWeatherUiModel()) }
    val currentWeatherViewState = _currentWeatherViewState.asStateFlow()

    init {
        fetchLocation()
    }

    fun fetchCurrentWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        repository.getCurrentWeather(latitude, longitude).collectLatest {
            Timber.d("fetchCurrentWeather == $it")
            if (it != null) {
                _currentWeatherViewState.value = it.toCurrentWeatherUiModel()
            }
        }
    }

    fun fetchLocation() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                fetchCurrentWeather(latitude = location.latitude, longitude = location.longitude)
            }
        }
    }

}