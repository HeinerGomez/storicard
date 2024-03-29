package com.avility.data.repository

import com.avility.domain.model.UserModel
import com.avility.domain.repository.UserRepository
import com.avility.shared.core.constants.Constants.USERS_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : UserRepository {
    override suspend fun addUser(user: UserModel) {
        fireStore.collection(USERS_COLLECTION)
            .document(user.id)
            .set(user)
            .await()
    }

    override suspend fun auth(email: String, pwd: String) {
        firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
            } else {
                // handle error
            }
        }
    }
}