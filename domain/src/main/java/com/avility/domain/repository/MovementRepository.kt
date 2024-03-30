package com.avility.domain.repository

import com.avility.domain.model.MovementModel
import com.avility.shared.core.constants.Resource
import kotlinx.coroutines.flow.Flow

interface MovementRepository {
    fun getMovementsOfCard(cardId: String): Flow<Resource<List<MovementModel>>>
}