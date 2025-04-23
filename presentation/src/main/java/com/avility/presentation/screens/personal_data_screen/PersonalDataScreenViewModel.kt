package com.avility.presentation.screens.personal_data_screen

import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.CreateUserUseCase
import com.avility.domain.usescases.form_validation.ValidateEmailUseCase
import com.avility.domain.usescases.form_validation.ValidateGenericFieldUseCase
import com.avility.domain.usescases.form_validation.ValidatePasswordUseCase
import com.avility.presentation.screens.BaseViewModel
import com.avility.presentation.ui_models.PersonalDataUIModel
import com.avility.presentation.ui_models.toUserModel
import com.avility.presentation.validations.FieldValidation
import com.avility.presentation.validations.KeyField
import com.avility.presentation.validations.isValidForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalDataScreenViewModel @Inject constructor(
    private val validateGenericFieldUseCase: ValidateGenericFieldUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val createUserUseCase: CreateUserUseCase
) : BaseViewModel<PersonalDataScreenState, PersonalDataScreenAction>(PersonalDataScreenState()) {

    override fun dispatchAction(action: PersonalDataScreenAction) {
        when (action) {
            PersonalDataScreenAction.ClearState -> setState(PersonalDataScreenState())
            is PersonalDataScreenAction.UpdatePersonalData -> updatePersonalData(action)
            PersonalDataScreenAction.OnNextButton -> onNextButton()
        }
    }

    private fun updatePersonalData(action: PersonalDataScreenAction.UpdatePersonalData) {
        viewModelScope.launch(Dispatchers.) {
            for (fieldToValidate in action.personalData.fieldsValidation) {
                validateField(action.personalData, fieldToValidate)
            }
        }
    }

    private fun validateField(data: PersonalDataUIModel, field: FieldValidation) {
        val errorResource = when (field.keyField) {
            KeyField.NAME -> validateGenericFieldUseCase(data.name)
            KeyField.SURNAME -> validateGenericFieldUseCase(data.surname)
            KeyField.EMAIL -> validateEmailUseCase(data.email)
            KeyField.PASSWORD -> validatePasswordUseCase(data.password)
            KeyField.REPEAT_PASSWORD -> validatePasswordUseCase(data.repeatPassword, true, data.password)
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

    private fun onNextButton() {
        viewModelScope.launch {
            setState(uiState.value.copy(
                isLoading = true,
                msgErrorResource = null
            ))

            val response = createUserUseCase(uiState.value.data.toUserModel()).first()

            setState(uiState.value.copy(
                isLoading = false,
                msgErrorResource = response.message,
                createAccountSuccessful = response.message == null
            ))
        }
    }
}