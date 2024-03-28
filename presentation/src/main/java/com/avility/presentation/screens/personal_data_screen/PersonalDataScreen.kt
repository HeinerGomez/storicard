package com.avility.presentation.screens.personal_data_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.presentation.R
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.ui.components.elements_forms.StoriButton
import com.avility.shared.ui.components.elements_forms.StoriTextField
import com.avility.shared.ui.constants.MeasureSmallDimen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PersonalDataScreen(
    navController: NavController,
    viewModel: PersonalDataScreenViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value
    val keyboardController = LocalSoftwareKeyboardController.current

    MainContainer(
        isLoading = false,
        header = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_topbar_personal_data_screen),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        headerPadding = false,
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            ) {
                Text(
                    text = stringResource(R.string.title_personal_data_screen),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Divider()
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X20.value))
                StoriTextField(
                    placeholder = stringResource(R.string.place_holder_name),
                    value = state.data.name,
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                name = value
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X06.value))
                StoriTextField(
                    placeholder = stringResource(R.string.place_holder_surname),
                    value = state.data.surname,
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                surname = value
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X06.value))
                StoriTextField(
                    placeholder = stringResource(R.string.place_holder_email),
                    value = state.data.email,
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                email = value
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X06.value))
                StoriTextField(
                    placeholder = stringResource(R.string.place_holder_password),
                    value = state.data.password,
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                password = value
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X06.value))
                StoriTextField(
                    placeholder = stringResource(R.string.place_holder_password_repeat),
                    value = state.data.repeatPassword,
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                repeatPassword = value
                            )
                        ))
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                verticalArrangement = Arrangement.Bottom
            ) {
                StoriButton(
                    text = stringResource(R.string.btn_next),
                    onTap = {
                        viewModel.dispatchAction(PersonalDataScreenAction.RedirectToPhotoScreen)
                    }
                )
            }
        }
    }

}