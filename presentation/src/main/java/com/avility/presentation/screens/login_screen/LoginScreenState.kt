package com.avility.presentation.screens.login_screen

import androidx.annotation.StringRes
import com.avility.presentation.ui_models.LoginDataUI

data class LoginScreenState(
    val isLoading: Boolean = false,
    val data: LoginDataUI = LoginDataUI(),
    val isValidForm: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    @StringRes
    val msgErrorResource: Int? = null
)
