package com.avility.storicard.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avility.shared.ui.Screen
import com.avility.presentation.screens.login_screen.LoginScreen
import com.avility.presentation.screens.personal_data_screen.PersonalDataScreen

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
    }
}