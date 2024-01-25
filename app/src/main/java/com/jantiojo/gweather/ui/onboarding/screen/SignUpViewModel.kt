package com.jantiojo.gweather.ui.onboarding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.UserCredentialRepository
import com.jantiojo.gweather.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: UserCredentialRepository
) : ViewModel() {

    private val _signUpState by lazy { MutableStateFlow<UiState<Boolean>>(UiState.Idle) }
    val signUpState = _signUpState.asStateFlow()

    fun doSignUp(username: String, password: String, confirmPassword: String) {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            _signUpState.value = UiState.Error("Password must not be empty")
            return
        }
        if (password != confirmPassword) {
            _signUpState.value = UiState.Error("Password didn't match")
            return
        }
        viewModelScope.launch {
            repository.saveCredential(
                username = username.trim(),
                password = password.trim()
            ).collectLatest { insertedSuccess ->
                updateSignUpStateOnInfoInserted(insertedSuccess)
            }
        }
    }

    fun updateSignUpStateOnInfoInserted(insertedSuccess: Boolean) {
        _signUpState.value = if (insertedSuccess) {
            UiState.Success(true)
        } else {
            UiState.Error("Account creation unsuccessful")
        }
    }

    fun resetState() {
        _signUpState.value = UiState.Idle
    }
}
