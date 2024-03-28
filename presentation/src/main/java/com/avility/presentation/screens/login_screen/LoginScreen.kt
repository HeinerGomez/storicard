package com.avility.presentation.screens.login_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.R
import com.avility.shared.ui.Screen
import com.avility.shared.ui.components.elements_forms.SearchTextField
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
                    painter = painterResource(R.drawable.storicard_logo),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
                Text(
                    text = "Bienvenido a Stori Card",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchTextField(
                    style = TextFieldStyle.Standard,
                    keyboardType = KeyboardType.Email,
                    placeholder = "Correo electrónico"
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
                SearchTextField(
                    style = TextFieldStyle.Standard,
                    keyboardType = KeyboardType.Password,
                    placeholder = "Contraseña"
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
                Button(
                    onClick = {
                        // viewModel.dispatchAction(LoginScreenAction.Login)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MeasureSmallDimen.DIMEN_X23.value),
                    shape = roundedShapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                   Text(text = "Iniciar sesión")
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
                    Text(text = "Registrarme")
                }
            }
        }
    }
}