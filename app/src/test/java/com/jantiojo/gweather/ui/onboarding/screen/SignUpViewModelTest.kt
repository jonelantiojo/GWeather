package com.jantiojo.gweather.ui.onboarding.screen

import com.google.common.truth.Truth.assertThat
import com.jantiojo.gweather.data.repository.UserCredentialRepository
import com.jantiojo.gweather.rule.MainDispatcherRule
import com.jantiojo.gweather.utils.UiState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {

    private lateinit var viewModel: SignUpViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var userCredentialRepository: UserCredentialRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = SignUpViewModel(userCredentialRepository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getLoggedInState() {
    }

    @Test
    fun signupVIewModel_signupSuccess_uiStateSuccess() = runTest {

        val isInserted = true
        assertThat(password).isEqualTo(confirmPassword)

        coEvery {
            userCredentialRepository.saveCredential(
                username,
                password
            )
        } returns flow { isInserted }

        viewModel.doSignUp(username, password, confirmPassword)
        viewModel.updateSignUpStateOnInfoInserted(insertedSuccess = isInserted)

        coEvery { userCredentialRepository.saveCredential(username, password) }

        val uiState = viewModel.signUpState.value

        assertThat(uiState).isInstanceOf(UiState.Success::class.java)
    }


    @Test
    fun signupVIewModel_signupSuccess_uiStateError() = runTest {
        val isInserted = false
        assertThat(password).isEqualTo(confirmPassword)

        coEvery {
            userCredentialRepository.saveCredential(
                username,
                password
            )
        } returns flow { isInserted }

        viewModel.doSignUp(username, password, confirmPassword)
        viewModel.updateSignUpStateOnInfoInserted(insertedSuccess = isInserted)

        coEvery { userCredentialRepository.saveCredential(username, password) }

        val uiState = viewModel.signUpState.value

        assertThat(uiState).isInstanceOf(UiState.Error::class.java)
    }

    companion object {
        private const val username = "jonjon"
        private const val password = "1234567"
        private const val confirmPassword = "1234567"
    }
}