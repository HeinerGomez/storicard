package com.avility.domain.usescases

import com.avility.domain.repository.UserRepository
import com.avility.shared.core.constants.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        pwd: String
    ): Flow<Resource<Boolean>> {
        return userRepository.auth(email, pwd)
    }
}