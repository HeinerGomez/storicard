package com.avility.presentation.screens.home_screen

import androidx.annotation.StringRes
import com.avility.presentation.ui_models.HomeDataUI

data class HomeScreenState(
    val isLoading: Boolean = false,
    val data: HomeDataUI? = null,
    @StringRes
    val msgErrorResourceForCard: Int? = null,
    @StringRes
    val msgErrorResourceForMovement: Int? = null
)
