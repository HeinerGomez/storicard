package com.avility.presentation.ui_models

import androidx.annotation.StringRes
import com.avility.domain.model.UserModel

data class PersonalDataUIModel(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val photo: String? = null,
    val fieldsValidation: List<FieldValidation> = listOf(
        FieldValidation.GenericField(KeyField.NAME),
        FieldValidation.GenericField(KeyField.SURNAME),
        FieldValidation.EmailField(KeyField.EMAIL),
        FieldValidation.PasswordField(KeyField.PASSWORD),
        FieldValidation.PasswordField(KeyField.REPEAT_PASSWORD)
    ),
    val currentKeyValidation: KeyField? = null
) {
    fun getErrorResource(keyField: KeyField): Int? {
        val fieldValidation = fieldsValidation.firstOrNull { it.keyField == keyField }

        return fieldValidation?.errorResource
    }
}

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

fun PersonalDataUIModel.toUserModel() = UserModel(
    id = email,
    name = name,
    surname = surname,
    pwd = password,
    email = email
)