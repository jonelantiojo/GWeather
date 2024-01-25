package com.jantiojo.gweather.ui.weather.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jantiojo.gweather.core.component.NetworkImage
import com.jantiojo.gweather.ui.theme.GWeatherTheme
import com.jantiojo.gweather.ui.theme.Purple40
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUIModelPreviewProvider
import com.jantiojo.gweather.ui.weather.screen.model.CurrentWeatherUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherItemComponent(modifier: Modifier = Modifier, weatherUiModel: CurrentWeatherUiModel) {
    Card(
        onClick = {},
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Purple40,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,

            )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            NetworkImage(
                imageUrl = weatherUiModel.weatherIcon,
                modifier = Modifier
                    .size(64.dp)
            )
            Column {
                Text(
                    text = weatherUiModel.weatherName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )

                Text(
                    text = weatherUiModel.weatherDescription,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun WeatherItemComponentPreview(@PreviewParameter(CurrentWeatherUIModelPreviewProvider::class) model: CurrentWeatherUiModel) {
    GWeatherTheme {
        WeatherItemComponent(weatherUiModel = model)
    }
}