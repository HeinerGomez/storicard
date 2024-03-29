package com.avility.presentation.ui_models

import com.avility.domain.model.CardModel
import com.avility.domain.model.MovementModel

data class HomeDataUI(
    val card: CardModel? = null,
    val movements: List<MovementModel> = emptyList()
)
