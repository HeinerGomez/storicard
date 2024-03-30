package com.avility.presentation.screens.login_screen

import com.avility.presentation.ui_models.LoginDataUI

sealed class LoginScreenAction {

    object ClearState : LoginScreenAction()
    object Login : LoginScreenAction()

    class UpdateLoginDataUI(val data: LoginDataUI) : LoginScreenAction()
}
