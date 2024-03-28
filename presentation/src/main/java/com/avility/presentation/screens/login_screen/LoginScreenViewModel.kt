package com.avility.presentation.screens.login_screen

import androidx.lifecycle.viewModelScope
import com.avility.domain.model.UserModel
import com.avility.domain.usescases.CreateUserUseCase
import com.avility.presentation.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : BaseViewModel<LoginScreenState, LoginScreenAction>(LoginScreenState()) {

    override fun dispatchAction(action: LoginScreenAction) {
        when (action) {
            LoginScreenAction.Login -> {

            }
        }
    }
}