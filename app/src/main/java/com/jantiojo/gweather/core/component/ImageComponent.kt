package com.jantiojo.gweather.core.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.jantiojo.gweather.R

@Composable
fun NetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Painter? = painterResource(id = R.drawable.placeholder_image_default),
    error: Painter? = painterResource(id = R.drawable.placeholder_image_default),
    description: String? = null,
    colorFilter: ColorFilter? = null,
    onSuccess: (AsyncImagePainter.State.Success) -> Unit = {},
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = placeholder,
        error = error,
        contentDescription = description,
        contentScale = contentScale,
        colorFilter = colorFilter,
        onSuccess = onSuccess,
        modifier = modifier
    )
}