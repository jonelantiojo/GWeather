package com.jantiojo.gweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.MainScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) : ViewModel() {
    private val _routeStartDestinationState by lazy { MutableStateFlow<String?>(null) }
    val routeStartDestinationState = _routeStartDestinationState.asStateFlow()

    init {
        fetchRouteStartDestination()
    }

    private fun fetchRouteStartDestination() {
        viewModelScope.launch {
            Timber.e(" destination ${mainScreenRepository.routeDestination().first()}")
            _routeStartDestinationState.value = mainScreenRepository.routeDestination().first()
        }
    }
}