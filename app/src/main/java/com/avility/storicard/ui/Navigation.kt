package com.avility.storicard.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avility.presentation.screens.home_screen.HomeScreen
import com.avility.shared.ui.Screen
import com.avility.presentation.screens.login_screen.LoginScreen
import com.avility.presentation.screens.personal_data_screen.PersonalDataScreen
import com.avility.presentation.screens.take_photo_screen.TakePhotoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(Screen.PersonalDataScreen.route) {
            PersonalDataScreen(navController)
        }
        composable(Screen.TakePhotoScreen.route+"/{userId}") {
            TakePhotoScreen(navController)
        }
        composable(Screen.HomeScreen.route+"/{userId}") {
            HomeScreen(navController)
        }
    }
}