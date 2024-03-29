package com.avility.domain.usescases

import com.avility.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        pwd: String
    ) {
        userRepository.auth(email, pwd)
    }
}