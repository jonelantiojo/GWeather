package com.jantiojo.gweather.ui.weather.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jantiojo.gweather.R
import com.jantiojo.gweather.core.component.DrawableIconComponent
import com.jantiojo.gweather.core.component.NetworkImage
import com.jantiojo.gweather.ui.theme.GWeatherTheme
import com.jantiojo.gweather.ui.theme.Purple40
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUIModelPreviewProvider
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel
import com.jantiojo.gweather.ui.weather.viewmodel.CurrentWeatherViewModel

@Composable
fun CurrentWeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val currentWeather by viewModel.currentWeatherViewState.collectAsStateWithLifecycle()
    CurrentWeatherContent(currentWeatherUiModel = currentWeather)
}

@Composable
private fun CurrentWeatherContent(
    modifier: Modifier = Modifier,
    currentWeatherUiModel: CurrentWeatherUiModel
) {
    Column(modifier = modifier.fillMaxSize()) {
        WeatherInfoCard(currentWeatherUiModel)
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            SunPlacementTimeInfo(
                modifier = Modifier.weight(1f),
                icon = R.drawable.sunrise,
                titleRes = R.string.sun_rise,
                time = currentWeatherUiModel.sunRiseTime
            )
            SunPlacementTimeInfo(
                modifier = Modifier.weight(1f),
                icon = R.drawable.sunset,
                titleRes = R.string.sun_set,
                time = currentWeatherUiModel.sunSetTime
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherInfoCard(currentWeatherUiModel: CurrentWeatherUiModel) {
    Card(
        onClick = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Purple40,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,

            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = currentWeatherUiModel.locationName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Black
                )
                Text(
                    text = currentWeatherUiModel.currentDateAndTime,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
                )
            }
            NetworkImage(
                imageUrl = currentWeatherUiModel.weatherIcon,
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = currentWeatherUiModel.weatherDescription,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )

            Text(
                text = currentWeatherUiModel.weatherTemperature,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SunPlacementTimeInfo(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @DrawableRes icon: Int,
    time: String
) {
    Card(
        onClick = {},
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Purple40,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,

            )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            DrawableIconComponent(iconRes = icon, modifier = Modifier.size(64.dp))
            Text(
                text = time,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun CurrentWeatherScreenPreview(@PreviewParameter(CurrentWeatherUIModelPreviewProvider::class) model: CurrentWeatherUiModel) {
    GWeatherTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CurrentWeatherContent(currentWeatherUiModel = model)
        }
    }
}
