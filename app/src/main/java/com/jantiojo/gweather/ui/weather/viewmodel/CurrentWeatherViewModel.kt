package com.jantiojo.gweather.ui.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.jantiojo.gweather.data.repository.GWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val repository: GWeatherRepository
) : ViewModel() {

}