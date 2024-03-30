package com.avility.presentation.validations

import androidx.annotation.StringRes

sealed class FieldValidation(
    @StringRes var errorResource: Int? = null,
    val keyField: KeyField
) {
    class GenericField(keyField: KeyField) : FieldValidation(null, keyField)
    class EmailField(keyField: KeyField) : FieldValidation(null, keyField)
    class PasswordField(keyField: KeyField) : FieldValidation(null, keyField)
}

fun List<FieldValidation>.isValidForm(): Boolean {
    return !this.any { it.errorResource != null }
}

enum class KeyField {
    NAME,
    SURNAME,
    EMAIL,
    PASSWORD,
    REPEAT_PASSWORD
}