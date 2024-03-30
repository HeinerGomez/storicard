package com.avility.domain.repository

import com.avility.domain.model.UserModel
import com.avility.shared.core.constants.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user: UserModel): Flow<Resource<UserModel>>

    suspend fun auth(email: String, pwd: String)
}