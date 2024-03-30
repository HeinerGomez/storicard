package com.avility.domain.usescases

import com.avility.domain.model.UserModel
import com.avility.domain.repository.UserRepository
import com.avility.shared.core.constants.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        user: UserModel
    ): Flow<Resource<UserModel>> {
        return userRepository.addUser(user)
    }
}