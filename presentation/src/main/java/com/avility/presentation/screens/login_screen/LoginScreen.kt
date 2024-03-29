package com.avility.presentation.screens.login_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.R as SharedResource
import com.avility.presentation.R
import com.avility.shared.ui.Screen
import com.avility.shared.ui.components.elements_forms.StoriTextField
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.roundedShapes
import com.avility.shared.ui.styles.elements_forms.TextFieldStyle

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    MainContainer(
        isLoading = false
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            HeaderLogin(this)
            FormLogin(navController, viewModel, this)
        }
    }
}

@Composable
private fun HeaderLogin(scope: ColumnScope) {
    scope.run {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(SharedResource.drawable.storicard_logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
            Text(
                text = stringResource(R.string.welcome_stori_card),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

@Composable
private fun FormLogin(
    navController: NavController,
    viewModel: LoginScreenViewModel,
    scope: ColumnScope
) {
    val data = viewModel.uiState.value.data

    scope.run {
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StoriTextField(
                style = TextFieldStyle.Standard,
                keyboardType = KeyboardType.Email,
                placeholder = stringResource(R.string.place_holder_email),
                value = data.email,
                onTextChange = { value ->
                    viewModel.dispatchAction(LoginScreenAction.UpdateLoginDataUI(
                        data.copy(
                            email = value
                        )
                    ))
                }
            )
            Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
            StoriTextField(
                style = TextFieldStyle.Standard,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = stringResource(R.string.place_holder_password),
                value = data.password,
                onTextChange = { value ->
                    viewModel.dispatchAction(LoginScreenAction.UpdateLoginDataUI(
                        data.copy(
                            password = value
                        )
                    ))
                }
            )
            Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
            Button(
                onClick = {
                    viewModel.dispatchAction(LoginScreenAction.Login)
                    // navController.navigate(Screen.HomeScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MeasureSmallDimen.DIMEN_X23.value),
                shape = roundedShapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = stringResource(R.string.btn_login))
            }
            Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.PersonalDataScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MeasureSmallDimen.DIMEN_X23.value),
                shape = roundedShapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                border = BorderStroke(MeasureSmallDimen.DIMEN_X01.value, MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(text = stringResource(R.string.btn_signup))
            }
        }
    }
}