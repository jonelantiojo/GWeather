package com.jantiojo.gweather.data.repository

import app.cash.turbine.testIn
import com.google.common.truth.Truth.assertThat
import com.jantiojo.gweather.data.datasource.UserCredentialLocalDataSource
import com.jantiojo.gweather.data.entity.UserCredentialEntity
import com.jantiojo.gweather.rule.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class UserCredentialRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var localDataSource: UserCredentialLocalDataSource

    private lateinit var repository: UserCredentialRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = UserCredentialRepository(
            localDataSource,
            mainDispatcherRule.testDispatcher
        )
    }


    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun userCredentialRepository_CallSaveCredential_InsertCredentialToDataBase() = runTest {

        coEvery { localDataSource.insertUserCredential(userCredentialTest) } returns true

        val resultTurbine = repository.saveCredential(USERNAME_TEST, PASSWORD_TEST)
            .testIn(this)

        val isInsertedSuccessFul = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(isInsertedSuccessFul).isTrue()

        coVerify {
            localDataSource.insertUserCredential(userCredentialTest)
        }
    }


    @Test
    fun userCredentialRepository_CallSaveCredential_CredentialNotInsertedToDataBase() = runTest {

        coEvery { localDataSource.insertUserCredential(userCredentialTest) } returns false

        val resultTurbine = repository.saveCredential(USERNAME_TEST, PASSWORD_TEST)
            .testIn(this)

        val isInsertedSuccessFul = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(isInsertedSuccessFul).isFalse()

        coVerify {
            localDataSource.insertUserCredential(userCredentialTest)
        }
    }

    @Test
    fun userCredentialRepository_CallGetAllUserCredentials_GetCredentialList() = runTest {

        coEvery { localDataSource.getAllCredentials() } returns allCredentialListTest

        val resultTurbine = repository.getAllUserCredentials()
            .testIn(this)

        val credentials = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(credentials).isNotEmpty()
        assertThat(credentials).hasSize(2)
        assertThat(credentials).isEqualTo(allCredentialListTest)

        coVerify {
            localDataSource.getAllCredentials()
        }
    }

    @Test
    fun userCredentialRepository_CallGetAllUserCredentials_GetEmptyCredential() = runTest {

        coEvery { localDataSource.getAllCredentials() } returns emptyList()

        val resultTurbine = repository.getAllUserCredentials()
            .testIn(this)

        val credentials = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(credentials).isEmpty()
        assertThat(credentials).hasSize(0)

        coVerify {
            localDataSource.getAllCredentials()
        }
    }

    companion object {
        private const val USERNAME_TEST = "myUsername"
        private const val PASSWORD_TEST = "234412442"
        private val userCredentialTest = UserCredentialEntity(
            username = USERNAME_TEST,
            password = PASSWORD_TEST
        )
        private val allCredentialListTest = listOf(
            UserCredentialEntity(
                username = USERNAME_TEST,
                password = PASSWORD_TEST
            ),
            UserCredentialEntity(
                username = "myUsername12",
                password = "PASSWORD_TEST"
            )
        )
    }
}