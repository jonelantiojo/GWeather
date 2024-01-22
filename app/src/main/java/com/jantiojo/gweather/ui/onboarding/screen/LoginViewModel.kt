package com.jantiojo.gweather.ui.onboarding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.UserCredentialRepository
import com.jantiojo.gweather.ui.onboarding.screen.model.LoginUiModel
import com.jantiojo.gweather.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserCredentialRepository
) : ViewModel() {

    private val _loggedInState by lazy { MutableStateFlow<UiState<Boolean>>(UiState.Idle) }
    val loggedInState = _loggedInState.asStateFlow()
    fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            repository.getAllUserCredentials().collectLatest { users ->
                val credentials = users.map {
                    LoginUiModel(
                        username = it.username,
                        password = it.password
                    )
                }

                Timber.d(
                    "test == ${
                        credentials.contains(
                            LoginUiModel(
                                username = username,
                                password = password
                            )
                        )
                    }"
                )
                if (credentials.contains(
                        LoginUiModel(
                            username = username,
                            password = password
                        )
                    )
                ) {
                    _loggedInState.value = UiState.Success(true)
                } else {
                    _loggedInState.value = UiState.Error("Login Error")
                }
            }
        }
    }


    fun resetState() {
        _loggedInState.value = UiState.Idle
    }
}
