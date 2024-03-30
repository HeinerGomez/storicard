package com.avility.domain.usescases.form_validation

import android.util.Patterns
import androidx.annotation.StringRes
import com.avility.shared.R
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Int? {
        @StringRes
        var strResource: Int? = null

        if (email.isBlank()) {
            strResource = R.string.email_is_blank
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            strResource = R.string.invalid_email
        }

        return strResource
    }

}