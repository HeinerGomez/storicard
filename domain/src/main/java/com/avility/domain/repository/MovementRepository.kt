package com.avility.domain.repository

import com.avility.domain.model.MovementModel
import kotlinx.coroutines.flow.Flow

interface MovementRepository {
    fun getMovementsOfCard(cardId: String): Flow<List<MovementModel>>
}