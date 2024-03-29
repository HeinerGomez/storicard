package com.avility.presentation.screens.login_screen

import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.LoginUseCase
import com.avility.presentation.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginScreenState, LoginScreenAction>(LoginScreenState()) {

    override fun dispatchAction(action: LoginScreenAction) {
        when (action) {
            LoginScreenAction.Login -> login()
            is LoginScreenAction.UpdateLoginDataUI -> setState(uiState.value.copy(
                data = action.data,
            ))
        }
    }

    private fun login() {
        viewModelScope.launch {
            val data = uiState.value.data
            loginUseCase(data.email, data.password)
        }
    }
}