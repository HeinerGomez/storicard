package com.avility.presentation.screens.personal_data_screen

import androidx.annotation.StringRes
import com.avility.presentation.ui_models.PersonalDataUIModel

data class PersonalDataScreenState(
    val isLoading: Boolean = false,
    val data: PersonalDataUIModel = PersonalDataUIModel(),
    val isValidForm: Boolean = true,
    @StringRes
    val msgErrorResource: Int? = null,
    val createAccountSuccessful: Boolean = false
)
