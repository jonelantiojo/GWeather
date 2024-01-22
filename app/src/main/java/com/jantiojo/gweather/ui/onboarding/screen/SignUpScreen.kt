package com.jantiojo.gweather.ui.onboarding.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jantiojo.gweather.R
import com.jantiojo.gweather.core.component.DrawableIconComponent
import com.jantiojo.gweather.core.component.SimpleDialogComponent
import com.jantiojo.gweather.ui.theme.GWeatherTheme
import com.jantiojo.gweather.utils.UiState

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    var openDialog by remember { mutableStateOf(false) }

    val uiState by viewModel.signUpState.collectAsStateWithLifecycle()

    SignUpScreenContent { username, password, confirmPass ->
        viewModel.doSignUp(username, password, confirmPass)
    }

    LaunchedEffect(key1 = uiState) {
        when (uiState) {
            is UiState.Success -> {
                onNavigateToLogin()
                viewModel.resetState()
            }

            is UiState.Error -> {
                openDialog = true
                viewModel.resetState()
            }

            else -> Unit
        }
    }

    if (openDialog) {
        SimpleDialogComponent(
            message = stringResource(id = R.string.password_did_not_match),
            onDismissDialog = { openDialog = false },
            onConfirm = { openDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreenContent(onSignUpSuccess: (username: String, password: String, confirmPassword: String) -> Unit) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

        DrawableIconComponent(iconRes = R.drawable.weather)
        Text(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.Serif),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                OutlinedTextField(
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.username)) },
                    value = username.value,
                    onValueChange = { username.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.password)) },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(id = R.string.confirm_password)) },
                    value = confirmPassword.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { confirmPassword.value = it }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    onSignUpSuccess(
                        username.value.text,
                        password.value.text,
                        confirmPassword.value.text
                    )
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.sign_up))
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    GWeatherTheme {
        SignUpScreenContent(onSignUpSuccess = { _, _, _ -> })
    }
}