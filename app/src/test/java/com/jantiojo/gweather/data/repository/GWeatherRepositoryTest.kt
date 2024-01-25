package com.jantiojo.gweather.data.repository

import app.cash.turbine.testIn
import com.google.common.truth.Truth.assertThat
import com.jantiojo.gweather.data.datasource.GWeatherLocalDataSource
import com.jantiojo.gweather.data.datasource.GWeatherRemoteDataSource
import com.jantiojo.gweather.data.entity.CurrentWeatherEntity
import com.jantiojo.gweather.rule.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.Ordering
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GWeatherRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var remoteDataSource: GWeatherRemoteDataSource

    @MockK
    lateinit var localDataSource: GWeatherLocalDataSource

    private lateinit var repository: GWeatherRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = GWeatherRepository(
            remoteDataSource,
            localDataSource,
            mainDispatcherRule.testDispatcher
        )
    }


    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun gWeatherRepository_CallGetCurrentWeather_GetDataFromServer() = runTest {

        coEvery { remoteDataSource.getCurrentWeather("$LAT_TEST", "$LONG_TEST") } returns currentWeatherEntityTest

        val resultTurbine = repository.getCurrentWeather(
            latitude = LAT_TEST,
            longitude = LONG_TEST
        ).testIn(this)

        val weather = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(weather).isNotNull()
        assertThat(weather?.weatherDescription).isEqualTo("Very Cloudy")
        assertThat(weather?.weatherTemperature).isEqualTo(27.0)
        assertThat(weather?.countryName).isEqualTo("PH")

        coVerify {
            remoteDataSource.getCurrentWeather("$LAT_TEST", "$LONG_TEST")
        }
    }

    @Test
    fun gWeatherRepository_CallGetCurrentWeather_SaveWeatherInLocalCached() = runTest {
        coEvery { remoteDataSource.getCurrentWeather("$LAT_TEST", "$LONG_TEST") } returns currentWeatherEntityTest

        val resultTurbine = repository.getCurrentWeather(
            latitude = LAT_TEST,
            longitude = LONG_TEST
        ).testIn(this)

        val weather = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(weather).isNotNull()
        assertThat(weather?.weatherDescription).isEqualTo("Very Cloudy")
        assertThat(weather?.weatherTemperature).isEqualTo(27.0)
        assertThat(weather?.countryName).isEqualTo("PH")

        coVerify(ordering = Ordering.ORDERED) {
            remoteDataSource.getCurrentWeather("$LAT_TEST", "$LONG_TEST")
            localDataSource.insertWeather(currentWeatherEntityTest)
        }
    }

    @Test
    fun gWeatherRepository_CallGetCurrentWeather_DoNotSaveWeatherInLocalCached() = runTest {

        coEvery { remoteDataSource.getCurrentWeather("$LAT_TEST", "$LONG_TEST") } returns null

        val resultTurbine = repository.getCurrentWeather(
            latitude = LAT_TEST,
            longitude = LONG_TEST
        ).testIn(this)

        val weather = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(weather).isNull()
        assertThat(weather?.weatherDescription).isNull()
        assertThat(weather?.weatherTemperature).isNull()
        assertThat(weather?.countryName).isNull()

        coVerify {
            remoteDataSource.getCurrentWeather("$LAT_TEST", "$LONG_TEST")
        }

        coVerify(inverse = true) {
            localDataSource.insertWeather(currentWeatherEntityTest)
        }
    }


    @Test
    fun gWeatherRepository_CallGetAllWeather_GetWeatherList() = runTest {

        coEvery { localDataSource.getAllWeather() } returns allWeatherListTest

        val resultTurbine = repository.getAllWeather().testIn(this)

        val weathers = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(weathers).isNotEmpty()
        assertThat(weathers).hasSize(2)
        assertThat(weathers).isEqualTo(allWeatherListTest)

        coVerify {
            localDataSource.getAllWeather()
        }
    }

    @Test
    fun gWeatherRepository_CallGetAllWeather_GetEmptyWeatherList() = runTest {

        coEvery { localDataSource.getAllWeather() } returns emptyList()

        val resultTurbine = repository.getAllWeather().testIn(this)

        val weathers = resultTurbine.awaitItem()
        resultTurbine.awaitComplete()

        assertThat(weathers).isEmpty()
        assertThat(weathers).isNotEqualTo(allWeatherListTest)
        coVerify {
            localDataSource.getAllWeather()
        }
    }

    companion object {
        private const val LAT_TEST = 123.00132
        private const val LONG_TEST = 140.00132

        private val currentWeatherEntityTest = CurrentWeatherEntity(
            id = 0,
            latitude = 0.0,
            longitude = 0.0,
            weatherName = "Cloudy",
            weatherDescription = "Very Cloudy",
            weatherTemperature = 27.0,
            weatherIcon = "c",
            currentDateUtc = 1232323,
            countryName = "PH",
            sunRiseUtc = 123213,
            sunSetUtc = 21312321,
            cityName = "Taguig City"
        )

        private val allWeatherListTest = listOf(
            CurrentWeatherEntity(
                id = 1,
                latitude = 0.0,
                longitude = 0.0,
                weatherName = "Cloudy",
                weatherDescription = "Very Cloudy",
                weatherTemperature = 27.0,
                weatherIcon = "c",
                currentDateUtc = 1232323,
                countryName = "PH",
                sunRiseUtc = 123213,
                sunSetUtc = 21312321,
                cityName = "Taguig City"
            ),
            CurrentWeatherEntity(
                id = 2,
                latitude = 0.0,
                longitude = 0.0,
                weatherName = "Cloudy",
                weatherDescription = "Very Cloudy",
                weatherTemperature = 27.0,
                weatherIcon = "c",
                currentDateUtc = 1232323,
                countryName = "PH",
                sunRiseUtc = 123213,
                sunSetUtc = 21312321,
                cityName = "Taguig City"
            )
        )
    }
}