package com.avility.presentation.ui_models

import com.avility.presentation.validations.FieldValidation
import com.avility.presentation.validations.KeyField

data class LoginDataUI(
    val email: String = "",
    val password: String = "",
    val fieldsValidation: List<FieldValidation> = listOf(
        FieldValidation.EmailField(KeyField.EMAIL),
        FieldValidation.PasswordField(KeyField.PASSWORD)
    )
) {
    fun getErrorResource(keyField: KeyField): Int? {
        val fieldValidation = fieldsValidation.firstOrNull { it.keyField == keyField }

        return fieldValidation?.errorResource
    }
}
