package com.avility.domain.usescases

import com.avility.domain.repository.CardRepository
import com.avility.domain.repository.MovementRepository
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCardUserDataUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val movementRepository: MovementRepository
){
    operator fun invoke(userId: String)  = combine(
        cardRepository.getCardData(userId),
        movementRepository.getMovementsOfCard(userId)
    ) { cardResource, movementResource ->
        Pair(cardResource, movementResource)
    }
}