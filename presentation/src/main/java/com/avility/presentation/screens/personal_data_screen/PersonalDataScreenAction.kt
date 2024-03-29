package com.avility.presentation.screens.personal_data_screen

import com.avility.presentation.ui_models.PersonalDataUIModel

sealed class PersonalDataScreenAction {
    object RedirectToPhotoScreen : PersonalDataScreenAction()

    class UpdatePersonalData(val personalData: PersonalDataUIModel) : PersonalDataScreenAction()
}