@file:OptIn(KoinExperimentalAPI::class)

package org.khairy.brova

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.khairy.brova.design.ProvaFontFamily
import org.khairy.brova.features.login.ui.LoginScreen
import org.khairy.brova.features.login.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App() {
   // val loginViewModel = koinViewModel<LoginViewModel>()
    MaterialTheme(
        typography = Typography(defaultFontFamily = ProvaFontFamily())
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            // Your UI Code
            LoginScreen()
        }
    }
}