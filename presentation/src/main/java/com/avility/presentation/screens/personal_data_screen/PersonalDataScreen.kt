package com.avility.presentation.screens.personal_data_screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.avility.presentation.R
import com.avility.presentation.ui_models.PersonalDataUIModel
import com.avility.presentation.validations.KeyField
import com.avility.shared.ui.Screen
import com.avility.shared.ui.components.containers.BasicContainer
import com.avility.shared.ui.components.containers.MainContainer
import com.avility.shared.ui.components.elements_forms.StoriButton
import com.avility.shared.ui.components.elements_forms.StoriTextField
import com.avility.shared.ui.constants.MeasureSmallDimen
import com.avility.shared.ui.constants.dangerColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataScreen(
    navController: NavController,
    viewModel: PersonalDataScreenViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.value

    if (state.createAccountSuccessful) {
        viewModel.dispatchAction(PersonalDataScreenAction.ClearState)
        navController.navigate(Screen.TakePhotoScreen.route + "/${state.data.email}") {
            popUpTo(0)
        }
    }

    MainContainer(
        isLoading = state.isLoading,
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
                    .weight(0.9f)
            ) {
                Text(
                    text = stringResource(R.string.title_personal_data_screen),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X10.value))
                Divider()
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X20.value))
                FormTextField(
                    value =  state.data.name,
                    placeholderRes = R.string.place_holder_name,
                    data = state.data,
                    keyField = KeyField.NAME,
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                name = value,
                                currentKeyValidation = KeyField.NAME
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                FormTextField(
                    value =  state.data.surname,
                    placeholderRes = R.string.place_holder_surname,
                    data = state.data,
                    keyField = KeyField.SURNAME,
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                surname = value,
                                currentKeyValidation = KeyField.SURNAME
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                FormTextField(
                    value =  state.data.email,
                    placeholderRes = R.string.place_holder_email,
                    data = state.data,
                    keyField = KeyField.EMAIL,
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                email = value,
                                currentKeyValidation = KeyField.EMAIL
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                FormTextField(
                    value =  state.data.password,
                    placeholderRes = R.string.place_holder_password,
                    data = state.data,
                    keyField = KeyField.PASSWORD,
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                password = value,
                                currentKeyValidation = KeyField.PASSWORD
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X04.value))
                FormTextField(
                    value =  state.data.repeatPassword,
                    placeholderRes = R.string.place_holder_password_repeat,
                    data = state.data,
                    keyField = KeyField.REPEAT_PASSWORD,
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChange = { value ->
                        viewModel.dispatchAction(PersonalDataScreenAction.UpdatePersonalData(
                            state.data.copy(
                                repeatPassword = value,
                                currentKeyValidation = KeyField.REPEAT_PASSWORD
                            )
                        ))
                    }
                )
                Spacer(modifier = Modifier.height(MeasureSmallDimen.DIMEN_X03.value))
                state.msgErrorResource?.let {
                    DangerMessageRibbon(stringResource(it), this@Column)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                StoriButton(
                    text = stringResource(R.string.btn_next),
                    onTap = {
                        viewModel.dispatchAction(PersonalDataScreenAction.OnNextButton)
                    },
                    enabled = state.isValidForm
                )
            }
        }
    }
}

@Composable
private fun FormTextField(
    value: String,
    @StringRes placeholderRes: Int,
    data: PersonalDataUIModel,
    keyField: KeyField,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChange: (value: String) -> Unit
) {
    StoriTextField(
        placeholder = stringResource(placeholderRes),
        value = value,
        onTextChange = onTextChange,
        visualTransformation = visualTransformation
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = data.getErrorResource(keyField)?.let {
            stringResource(it)
        }.orEmpty(),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.labelMedium.copy(
            color = MaterialTheme.colorScheme.error
        )
    )
}

@Composable
private fun DangerMessageRibbon(value: String, scope: ColumnScope) {
    scope.run {
        BasicContainer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MeasureSmallDimen.DIMEN_X30.value),
            containerColor = dangerColor
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}