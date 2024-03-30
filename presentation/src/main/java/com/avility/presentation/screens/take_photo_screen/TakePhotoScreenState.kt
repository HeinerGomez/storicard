package com.avility.presentation.screens.take_photo_screen

import androidx.annotation.StringRes

data class TakePhotoScreenState(
    val isLoading: Boolean = false,
    val isPhotoSaved: Boolean = false,
    @StringRes
    val msgErrorResource: Int? = null
)
