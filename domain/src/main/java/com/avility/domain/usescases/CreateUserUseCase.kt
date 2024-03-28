package com.avility.domain.usescases

import com.avility.domain.model.UserModel
import com.avility.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(
        user: UserModel
    ) {
        userRepository.addUser(user)
    }
}