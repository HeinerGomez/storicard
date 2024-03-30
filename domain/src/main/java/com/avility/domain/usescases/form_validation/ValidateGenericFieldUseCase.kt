package com.avility.domain.usescases.form_validation

import androidx.annotation.StringRes
import com.avility.shared.R
import javax.inject.Inject

class ValidateGenericFieldUseCase @Inject constructor() {

    operator fun invoke(value: String): Int? {
        @StringRes var errorResource: Int? = null
        if (value.isBlank()) {
            errorResource = R.string.required_field
        }

        return errorResource
    }

}