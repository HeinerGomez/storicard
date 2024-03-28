package com.avility.presentation.screens.personal_data_screen

import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.CreateUserUseCase
import com.avility.presentation.screens.BaseViewModel
import com.avility.presentation.ui_models.toUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalDataScreenViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : BaseViewModel<PersonalDataScreenState, PersonalDataScreenAction>(PersonalDataScreenState()) {
    override fun dispatchAction(action: PersonalDataScreenAction) {
        when (action) {
            PersonalDataScreenAction.RedirectToPhotoScreen -> {
                viewModelScope.launch {
                    createUserUseCase(uiState.value.data.toUserModel())
                }
            }
            is PersonalDataScreenAction.UpdatePersonalData -> updatePersonalData(action)
        }
    }

    private fun updatePersonalData(action: PersonalDataScreenAction.UpdatePersonalData) {
        viewModelScope.launch {
            setState(uiState.value.copy(
                data = action.personalData
            ))
        }
    }
}