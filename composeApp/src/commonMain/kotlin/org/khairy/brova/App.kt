@file:OptIn(KoinExperimentalAPI::class)

package org.khairy.brova

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.khairy.brova.design.AppColors

import org.khairy.brova.design.ProvaFontFamily
import org.khairy.brova.features.login.ui.LoginScreen
import org.khairy.brova.features.register.ui.RegisterScreen
import org.khairy.brova.navigation.ProvaLoginNavigation
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App() {

    MaterialTheme(
        //colors = ProvaColors(),
        typography = Typography(defaultFontFamily = ProvaFontFamily())
    ) {
        val navController = rememberNavController()
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            ProvaLoginNavigation(navController)
        }
    }
}

/*fun ProvaColors(): Colors {
    return Colors(
        primary = AppColors.blue_0072CE,

    )
}*/

