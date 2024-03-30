package com.avility.data.repository

import android.net.Uri
import com.avility.shared.R
import com.avility.domain.model.UserModel
import com.avility.domain.repository.UserRepository
import com.avility.shared.core.constants.Constants.USERS_COLLECTION
import com.avility.shared.core.constants.Constants.PHOTO_FOLDER
import com.avility.shared.core.constants.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage
) : UserRepository {

    private suspend fun accountExists(email: String): Boolean {
        val documentSnapshot = fireStore.collection(USERS_COLLECTION).document(email).get().await()
        return documentSnapshot.exists()
    }

    override suspend fun addUser(user: UserModel): Flow<Resource<UserModel>> = callbackFlow {
        var response: Resource<UserModel>
        val accountExists = accountExists(user.email)

        runCatching {
            if (!accountExists) {
                firebaseAuth.createUserWithEmailAndPassword(user.email, user.pwd).addOnCompleteListener { createUserTask ->
                    if (createUserTask.isSuccessful) {
                        fireStore.collection(USERS_COLLECTION).document(user.id).set(user)
                            .addOnCompleteListener { task ->
                                response = if (task.isSuccessful) {
                                    Resource.Success(user.copy(
                                        id = user.id
                                    ))
                                } else {
                                    Resource.Error(
                                        message = R.string.error_when_is_trying_create_account,
                                        data = user
                                    )
                                }

                                trySend(response).isSuccess
                            }
                    } else {
                        response = Resource.Error(
                            message = R.string.error_when_is_trying_create_account,
                            data = user
                        )
                        trySend(response).isSuccess
                    }
                }.await()
            } else {
                response = Resource.Error(
                    message = R.string.email_already_taken,
                    data = user
                )
                trySend(response).isSuccess
            }
        }.onFailure {
            response = Resource.Error(
                message = R.string.error_when_is_trying_create_account,
                data = user
            )
            trySend(response).isSuccess
        }

        awaitClose {  }
    }

    override suspend fun updatePhotoUser(email: String, photoPath: Uri): Flow<Resource<Boolean>> = callbackFlow {
        runCatching {
            val storageRef = firebaseStorage.reference.child(PHOTO_FOLDER)

            val fileRef = storageRef.child(email)
            val uploadTask = fileRef.putFile(photoPath)
            uploadTask.await()
        }.onSuccess {
            trySend(Resource.Success(
                data = true
            )).isSuccess
        }.onFailure {
            trySend(Resource.Error(
                data = false,
                message = R.string.error_when_is_trying_update_photo
            ))
        }
        awaitClose()
    }

    override suspend fun auth(email: String, pwd: String): Flow<Resource<Boolean>> = callbackFlow {
        runCatching {
            firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    if (user != null) {
                        trySend(Resource.Success(true)).isSuccess
                    } else {
                        trySend(Resource.Error(R.string.auth_failed)).isSuccess
                    }
                } else {
                    trySend(Resource.Error(R.string.auth_failed)).isSuccess
                }
            }
        }.onFailure {
            trySend(Resource.Error(R.string.auth_error)).isSuccess
        }

        awaitClose()
    }
}