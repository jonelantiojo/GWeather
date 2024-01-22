package com.jantiojo.gweather.ui.onboarding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantiojo.gweather.data.repository.UserCredentialRepository
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

    private val _successSignUp by lazy { MutableStateFlow(false) }
    val successSignUp = _successSignUp.asStateFlow()

    fun doSignUp(username: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) return
        viewModelScope.launch {
            repository.saveCredential(
                username = username,
                password = password
            ).collectLatest {
                _successSignUp.value = it
            }
        }
    }
}
