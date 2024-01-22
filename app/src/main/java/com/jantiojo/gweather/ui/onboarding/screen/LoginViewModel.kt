package com.jantiojo.gweather.ui.onboarding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.MainScreenRepository
import com.jantiojo.gweather.data.repository.UserCredentialRepository
import com.jantiojo.gweather.ui.Routes
import com.jantiojo.gweather.ui.onboarding.screen.model.LoginUiModel
import com.jantiojo.gweather.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserCredentialRepository,
    private val mainScreenRepository: MainScreenRepository
) : ViewModel() {

    private val _loggedInState by lazy { MutableStateFlow<UiState<Boolean>>(UiState.Idle) }
    val loggedInState = _loggedInState.asStateFlow()
    fun doLogin(username: String, password: String) {
        repository.getAllUserCredentials()
            .onEach {  users ->
                val credentials = users.map {
                    LoginUiModel(
                        username = it.username,
                        password = it.password
                    )
                }

                if (credentials.contains(
                        LoginUiModel(
                            username = username,
                            password = password
                        )
                    )
                ) {
                    mainScreenRepository.saveRouteStartDestination(Routes.Home.route)
                    _loggedInState.value = UiState.Success(true)
                } else {
                    _loggedInState.value = UiState.Error("Login Error")
                }
            }
            .launchIn(viewModelScope)
    }


    fun resetState() {
        _loggedInState.value = UiState.Idle
    }
}
