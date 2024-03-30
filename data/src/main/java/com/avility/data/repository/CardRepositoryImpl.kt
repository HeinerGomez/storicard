package com.avility.data.repository

import com.avility.shared.R
import com.avility.domain.model.CardModel
import com.avility.domain.repository.CardRepository
import com.avility.shared.core.constants.Constants.CARDS_COLLECTION
import com.avility.shared.core.constants.Constants.USER_ID_PARAM
import com.avility.shared.core.constants.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : CardRepository {
    override fun getCardData(userId: String): Flow<Resource<CardModel?>> = callbackFlow {
        runCatching {
            val creditCardsCollectionRef = fireStore.collection(CARDS_COLLECTION)
                .whereEqualTo(USER_ID_PARAM, userId)

            val subscription = creditCardsCollectionRef.addSnapshotListener { snapshot, _ ->
                snapshot?.let { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val document = querySnapshot.documents.first()

                        document.data?.let {
                            val cardModel = CardModel.fromMap(document.id, it)
                            trySend(Resource.Success(
                                cardModel
                            )).isSuccess
                        }
                    } else {
                        trySend(Resource.Success(
                            data = null
                        )).isSuccess
                    }
                }
            }

            awaitClose { subscription.remove() }
        }.onFailure {
            trySend(Resource.Error(
                message = R.string.get_card_info_failed
            )).isSuccess
        }

        awaitClose()
    }
}