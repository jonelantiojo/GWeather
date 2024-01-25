package com.jantiojo.gweather.ui.onboarding.screen

import com.google.common.truth.Truth.assertThat
import com.jantiojo.gweather.data.entity.UserCredentialEntity
import com.jantiojo.gweather.data.repository.MainScreenRepository
import com.jantiojo.gweather.data.repository.UserCredentialRepository
import com.jantiojo.gweather.rule.MainDispatcherRule
import com.jantiojo.gweather.ui.onboarding.screen.model.LoginUiModel
import com.jantiojo.gweather.utils.UiState
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var userCredentialRepository: UserCredentialRepository

    @MockK
    private lateinit var mainScreenRepository: MainScreenRepository

    private val accountList = listOf(
        UserCredentialEntity(
            username = "jonjon",
            password = "12345678"
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = LoginViewModel(
            userCredentialRepository,
            mainScreenRepository
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun loginViewModel_loginSuccess_uiStateSuccess() = runTest {
        val username = "jonjon"
        val password = "1234567"

        every { userCredentialRepository.getAllUserCredentials() } returns flow { accountList }
        val userCredential = accountList.map { LoginUiModel(it.username, it.password) }

        viewModel.doLogin(username, password)
        viewModel.checkUserCredential(username, password, userCredential)

        verify { userCredentialRepository.getAllUserCredentials() }

        val uiState = viewModel.loggedInState.value

        assertThat(uiState).isInstanceOf(UiState.Success::class.java)
    }


    @Test
    fun loginViewModel_loginSuccess_uiStateError() = runTest {
        val username = "jonjon1"
        val password = "12345671"

        every { userCredentialRepository.getAllUserCredentials() } returns flow { accountList }
        val userCredential = accountList.map { LoginUiModel(it.username, it.password) }

        viewModel.doLogin(username, password)
        viewModel.checkUserCredential(username, password, userCredential)

        verify { userCredentialRepository.getAllUserCredentials() }

        val uiState = viewModel.loggedInState.value

        assertThat(uiState).isInstanceOf(UiState.Error::class.java)
    }

}