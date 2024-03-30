package com.avility.domain.usescases

import android.net.Uri
import com.avility.domain.repository.UserRepository
import com.avility.shared.core.constants.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, photoFile: Uri): Flow<Resource<Boolean>> {
        return userRepository.updatePhotoUser(email, photoFile)
    }
}