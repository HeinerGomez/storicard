package com.avility.domain.usescases.form_validation

import androidx.annotation.StringRes
import com.avility.shared.R
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String, isForRepeatPassword: Boolean = false, otherPassword: String? = null): Int? {
        @StringRes
        var strResource: Int? = null

        if (password.isBlank()) {
            strResource = R.string.password_is_blank
        } else if (password.length < 6) {
            strResource = R.string.password_min_length
        } else if(password.length > 10) {
            strResource = R.string.password_max_length
        } else if(isForRepeatPassword) {
            otherPassword?.let {
                if (password != otherPassword) {
                    strResource = R.string.password_repeat_no_matches
                }
            }
        }

        return strResource
    }
}