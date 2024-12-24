package org.khairy.brova.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.khairy.brova.features.home.ui.HomeScreen
import org.khairy.brova.features.login.ui.LoginScreen
import org.khairy.brova.features.register.ui.RegisterScreen

@Composable
fun ProvaLoginNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = LoginScreen) {
        composable<LoginScreen> {
            LoginScreen(navController = navController)
        }

        composable<RegisterScreen> {
            RegisterScreen(navController = navController)
        }

        composable<HomeScreen> {
            HomeScreen(navController = navController)
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object RegisterScreen

@Serializable
object HomeScreen