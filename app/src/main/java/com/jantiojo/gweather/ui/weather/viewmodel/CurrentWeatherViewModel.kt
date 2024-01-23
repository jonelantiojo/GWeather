package com.jantiojo.gweather.ui.weather.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
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

    private fun fetchCurrentWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
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

    fun openAppSettings(context: Context) {
        val intent =
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
        ContextCompat.startActivity(context, intent, null)
    }
}