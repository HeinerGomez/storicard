package com.avility.shared.ui

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")

    object PersonalDataScreen : Screen("personal_data_screen")

    object TakePhotoScreen : Screen("take_photo_screen")

    object PhotoConfirmationScreen : Screen("photo_confirmation_screen")

    object HomeScreen : Screen("home_screen")

    object DetailMovementScreen : Screen("detail_movement_screen")
}
