package com.jantiojo.gweather.ui.weather.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jantiojo.gweather.R
import com.jantiojo.gweather.core.component.DrawableIconComponent
import com.jantiojo.gweather.ui.theme.GWeatherTheme
import com.jantiojo.gweather.ui.theme.Purple40

@Composable
fun CurrentWeatherScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        WeatherInfoCard()

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            SunPlacementTimeInfo(modifier = Modifier.weight(1f))
            SunPlacementTimeInfo(modifier = Modifier.weight(1f))
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherInfoCard() {
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
                    text = "Manila Philippines",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Black
                )
                Text(
                    text = "Monday Dec 19 | 09:00AM",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
                )
            }
            DrawableIconComponent(iconRes = R.drawable.moon, modifier = Modifier.size(100.dp))
            Text(
                text = "Sunny",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )

            Text(
                text = "33°C",
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
private fun SunPlacementTimeInfo(modifier: Modifier = Modifier) {
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
                text = "SunSet",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            DrawableIconComponent(iconRes = R.drawable.sunrise, modifier = Modifier.size(64.dp))
            Text(
                text = "10 PM",
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
private fun CurrentWeatherScreenPreview() {
    GWeatherTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CurrentWeatherScreen()
        }
    }
}
