@file:OptIn(KoinExperimentalAPI::class)

package org.khairy.brova

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.khairy.brova.design.ProvaFontFamily
import org.khairy.brova.features.brovaprefrences.BrovaSharedPrefs
import org.khairy.brova.features.login.ui.LoginScreen
import org.khairy.brova.navigation.HomeScreen
import org.khairy.brova.navigation.LoginScreen
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
            val sharedPrefs = BrovaSharedPrefs()
            val authToken = sharedPrefs.getAuthToken()
            val userData = sharedPrefs.getUserData()

            if (authToken.isEmpty()) {
                ProvaLoginNavigation(navController, LoginScreen)
            } else {
                ProvaLoginNavigation(navController, HomeScreen)
            }
        }
    }
}

/*fun ProvaColors(): Colors {
    return Colors(
        primary = AppColors.blue_0072CE,

    )
}*/

