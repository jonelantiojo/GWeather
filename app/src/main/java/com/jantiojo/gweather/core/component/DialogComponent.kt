package com.jantiojo.gweather.core.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import com.jantiojo.gweather.R

@Composable
fun SimpleDialogComponent(
    title: String = "",
    message: String = "",
    onDismissDialog: () -> Unit,
    onConfirm: () -> Unit,
    dismissContent: (@Composable () -> Unit)? = null,
    confirmContent: (@Composable () -> Unit)? = null,
) {
    AlertDialog(
        shape = MaterialTheme.shapes.medium,
        onDismissRequest = { onDismissDialog() },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = FontFamily.Default,
                    color = Color.Black
                )
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black
                )
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                if (confirmContent != null) {
                    confirmContent()
                } else {
                    Text(
                        text = stringResource(id = R.string.ok),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Default,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissDialog() }) {
                if (dismissContent != null) {
                    dismissContent()
                } else {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = FontFamily.Default,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        },
    )
}