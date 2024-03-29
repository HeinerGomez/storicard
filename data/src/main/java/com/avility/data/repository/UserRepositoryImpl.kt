package com.avility.data.repository

import com.avility.domain.model.UserModel
import com.avility.domain.repository.UserRepository
import com.avility.shared.core.constants.Constants.USERS_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : UserRepository {
    override suspend fun addUser(user: UserModel) {
        fireStore.collection(USERS_COLLECTION)
            .document(user.id)
            .set(user)
            .await()
    }
}