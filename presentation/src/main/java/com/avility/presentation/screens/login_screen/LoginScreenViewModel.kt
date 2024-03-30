package com.avility.presentation.screens.login_screen

import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.LoginUseCase
import com.avility.domain.usescases.form_validation.ValidateEmailUseCase
import com.avility.domain.usescases.form_validation.ValidatePasswordUseCase
import com.avility.presentation.screens.BaseViewModel
import com.avility.presentation.ui_models.LoginDataUI
import com.avility.presentation.validations.FieldValidation
import com.avility.presentation.validations.KeyField
import com.avility.presentation.validations.isValidForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : BaseViewModel<LoginScreenState, LoginScreenAction>(LoginScreenState()) {

    override fun dispatchAction(action: LoginScreenAction) {
        when (action) {
            LoginScreenAction.ClearState -> setState(LoginScreenState())
            LoginScreenAction.Login -> login()
            is LoginScreenAction.UpdateLoginDataUI -> updateDataLogin(action)
        }
    }

    private fun updateDataLogin(action: LoginScreenAction.UpdateLoginDataUI) {
        for (fieldToValidate in action.data.fieldsValidation) {
            validateField(action.data, fieldToValidate)
        }
    }

    private fun validateField(data: LoginDataUI, field: FieldValidation) {
        val errorResource = when (field.keyField) {
            KeyField.EMAIL -> validateEmailUseCase(data.email)
            KeyField.PASSWORD -> validatePasswordUseCase(data.password)
            else -> null
        }
        val fieldsValidation = uiState.value.data.fieldsValidation.map {
            if (it.keyField == field.keyField) {
                it.errorResource = errorResource
            }
            it
        }.toList()

        setState(uiState.value.copy(
            isLoading = false,
            data = data.copy(
                fieldsValidation = fieldsValidation
            ),
            isValidForm = fieldsValidation.isValidForm()
        ))
    }

    private fun login() {
        viewModelScope.launch {
            setState(uiState.value.copy(
                isLoading = true
            ))
            val data = uiState.value.data
            val response = loginUseCase(data.email, data.password).first()
            setState(uiState.value.copy(
                isLoading = false,
                isLoginSuccessful = response.message == null,
                msgErrorResource = response.message
            ))
        }
    }
}