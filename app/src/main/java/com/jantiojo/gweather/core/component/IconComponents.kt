package com.jantiojo.gweather.core.component

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jantiojo.gweather.R
import com.jantiojo.gweather.ui.theme.GWeatherTheme

@Composable
fun DrawableIconComponent(
    modifier: Modifier = Modifier,
    tintColor: Color = Color.Unspecified,
    @DrawableRes iconRes: Int
) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        tint = tintColor,
        modifier = modifier
    )
}


@Preview
@Composable
private fun DrawableIconComponentPreview() {
    GWeatherTheme {
        DrawableIconComponent(iconRes = R.drawable.rain)
    }
}