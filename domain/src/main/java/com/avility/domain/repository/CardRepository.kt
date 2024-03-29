package com.avility.domain.repository

import com.avility.domain.model.CardModel
import com.avility.domain.model.MovementModel
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getCardData(userId: String): Flow<CardModel?>
}