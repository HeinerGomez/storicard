package com.avility.data.repository

import com.avility.domain.model.MovementModel
import com.avility.domain.repository.MovementRepository
import com.avility.shared.core.constants.Constants.MOVEMENTS_COLLECTION
import com.avility.shared.core.constants.Constants.USER_ID_PARAM
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class MovementRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : MovementRepository {

    override fun getMovementsOfCard(userId: String): Flow<List<MovementModel>> = callbackFlow {
        val movementsCollection = fireStore.collection(MOVEMENTS_COLLECTION)
            .whereEqualTo(USER_ID_PARAM, userId)

        val subscription = movementsCollection.addSnapshotListener { snapshot, e ->
            val movements = mutableListOf<MovementModel>()
            snapshot?.let { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.data?.let {
                        val movement: MovementModel = MovementModel.fromMap(document.id, it)
                        movements.add(movement)
                    }
                }
                trySend(movements).isSuccess
            }
        }
        awaitClose { subscription.remove() }
    }
}