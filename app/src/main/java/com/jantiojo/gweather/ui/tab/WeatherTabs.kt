import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jantiojo.gweather.R
import com.jantiojo.gweather.ui.theme.TabIconColor
import com.jantiojo.gweather.ui.weather.screen.CurrentWeatherScreen
import com.jantiojo.gweather.ui.weather.screen.WeatherListScreen

@Composable
fun AnimatedBottomTabs() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        "Current Weather" to R.drawable.weather_tab,
        "Weather List" to R.drawable.list_tab
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Display content based on selected tab
        when (selectedTabIndex) {
            0 -> CurrentWeatherScreen()
            1 -> WeatherListScreen()
        }

        // Animated bottom tabs
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            tabs.forEachIndexed { index, (title, icon) ->
                AnimatedBottomTab(
                    modifier = Modifier.weight(1f),
                    title = title,
                    icon = icon,
                    isSelected = index == selectedTabIndex
                ) {
                    selectedTabIndex = index
                }
            }
        }
    }
}

@Composable
fun AnimatedBottomTab(
    modifier: Modifier,
    title: String,
    icon: Int,
    isSelected: Boolean,
    onTabSelected: () -> Unit
) {
    val scale by animateFloatAsState(if (isSelected) 1.2f else 1f, label = "")

    Box(
        modifier = modifier
            .clickable { onTabSelected() }
            .graphicsLayer(scaleX = scale, scaleY = scale)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Transparent,
                    RectangleShape
                )
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint =    if (isSelected) TabIconColor
                else Color.White,
                modifier = Modifier.size(24.dp)
            )
            if (isSelected) {
                Text(text = title, color = Color.White, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun AnimatedBottomTabsApp() {
    MaterialTheme {
        AnimatedBottomTabs()
    }
}

@Preview(showBackground = true)
@Composable
fun AnimatedBottomTabsAppPreview() {
    AnimatedBottomTabsApp()
}