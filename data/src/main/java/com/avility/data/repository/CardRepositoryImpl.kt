package com.avility.data.repository

import com.avility.domain.model.CardModel
import com.avility.domain.repository.CardRepository
import com.avility.shared.core.constants.Constants.CARDS_COLLECTION
import com.avility.shared.core.constants.Constants.USER_ID_PARAM
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : CardRepository {
    override fun getCardData(userId: String): Flow<CardModel?> = callbackFlow {
        val creditCardsCollectionRef = fireStore.collection(CARDS_COLLECTION)
            .whereEqualTo(USER_ID_PARAM, userId)

        val subscription = creditCardsCollectionRef.addSnapshotListener { snapshot, e ->
            snapshot?.let { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents.first()

                    document.data?.let {
                        val cardModel = CardModel.fromMap(document.id, it)
                        trySend(cardModel).isSuccess
                    }
                }
            }
        }

        awaitClose { subscription.remove() }
    }
}