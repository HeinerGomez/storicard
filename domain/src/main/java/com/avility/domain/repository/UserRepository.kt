package com.avility.domain.repository

import com.avility.domain.model.UserModel

interface UserRepository {

    suspend fun addUser(user: UserModel)

    suspend fun auth(email: String, pwd: String)
}