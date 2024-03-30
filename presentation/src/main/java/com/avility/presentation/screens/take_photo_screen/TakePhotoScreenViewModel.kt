package com.avility.presentation.screens.take_photo_screen

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.avility.domain.usescases.UpdateUserUseCase
import com.avility.presentation.screens.BaseViewModel
import com.avility.shared.core.extensions.toBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakePhotoScreenViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<TakePhotoScreenState, TakePhotoScreenAction>(TakePhotoScreenState()) {

    private var email: String? = null

    init {
        savedStateHandle.get<String>("userId")?.let { userId ->
            email = userId
        }
    }

    override fun dispatchAction(action: TakePhotoScreenAction) {
        when (action) {
            is TakePhotoScreenAction.SavePhoto -> savePhoto(action.photo)
            TakePhotoScreenAction.ClearState -> setState(TakePhotoScreenState())
        }
    }

    private fun savePhoto(photo: Uri) {
        viewModelScope.launch {
            email?.let {
                setState(uiState.value.copy(
                    isLoading = true,
                ))

                val response = updateUserUseCase(it, photo).first()

                setState(uiState.value.copy(
                    isLoading = false,
                    isPhotoSaved = response.message == null,
                    msgErrorResource = response.message
                ))
            }
        }
    }
}