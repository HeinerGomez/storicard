package com.avility.presentation.ui_models

import com.avility.domain.model.UserModel
import com.avility.presentation.validations.FieldValidation
import com.avility.presentation.validations.KeyField

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

fun PersonalDataUIModel.toUserModel() = UserModel(
    id = email,
    name = name,
    surname = surname,
    pwd = password,
    email = email
)