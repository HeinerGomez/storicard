package com.avility.presentation.screens.home_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.GetCardUserDataUseCase
import com.avility.shared.R
import com.avility.presentation.screens.BaseViewModel
import com.avility.presentation.ui_models.HomeDataUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getCardUserDataUseCase: GetCardUserDataUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeScreenState, HomeScreenAction>(HomeScreenState()) {

    init {
        viewModelScope.launch {
            setState(uiState.value.copy(
                isLoading = true
            ))

            savedStateHandle.get<String>("userId")?.let { userId ->
                getCardUserDataUseCase(userId).onEach {
                    setState(uiState.value.copy(
                        isLoading = false,
                        data = HomeDataUI(
                            card = it.first.data,
                            movements = it.second.data ?: emptyList()
                        ),
                        msgErrorResourceForCard = it.first.message,
                        msgErrorResourceForMovement = it.second.message
                    ))
                }.launchIn(this)
            } ?: setState(uiState.value.copy(
                isLoading = false,
                msgErrorResourceForCard = R.string.get_card_info_failed,
                msgErrorResourceForMovement = R.string.get_movement_info_failed
            ))
        }
    }

    override fun dispatchAction(action: HomeScreenAction) {}
}