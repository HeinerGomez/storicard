package com.avility.presentation.screens.home_screen

import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.GetCardUserDataUseCase
import com.avility.presentation.screens.BaseViewModel
import com.avility.presentation.ui_models.HomeDataUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCardUserDataUseCase: GetCardUserDataUseCase
) : BaseViewModel<HomeScreenState, HomeScreenAction>(HomeScreenState()) {

    init {
        viewModelScope.launch {
            setState(uiState.value.copy(
                isLoading = true
            ))
            delay(1_500L)
            getCardUserDataUseCase("agheinerag@gmail.com").onEach {
                setState(uiState.value.copy(
                    isLoading = false,
                    data = HomeDataUI(
                        card = it.first,
                        movements = it.second
                    )
                ))
            }.launchIn(this)
        }
    }

    override fun dispatchAction(action: HomeScreenAction) {

    }
}