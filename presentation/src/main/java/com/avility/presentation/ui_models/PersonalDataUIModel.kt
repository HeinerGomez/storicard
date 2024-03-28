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
    @StringRes
    val strResourceError: Int? = null
)

fun PersonalDataUIModel.toUserModel() = UserModel(
    id = email,
    name = name,
    surname = surname,
    pwd = password,
    email = email
)