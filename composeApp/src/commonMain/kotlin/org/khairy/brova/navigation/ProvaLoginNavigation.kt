package org.khairy.brova.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.khairy.brova.features.home.ui.HomeScreen
import org.khairy.brova.features.login.ui.LoginScreen
import org.khairy.brova.features.register.ui.RegisterScreen
import org.khairy.brova.features.savesizes.ui.MeasurementViewPager
import org.khairy.brova.features.scanqr.ui.ScanQRResultsScreen
import org.khairy.brova.features.scanqr.ui.ScanQRScreen

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

        dialog<ScanQRScreen> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.6f)
                    .clip(shape = RoundedCornerShape(16.dp)),
                elevation = 8.dp
            ) {
                ScanQRScreen {
                    navController.navigate(ScanQRResult(it))
                }
            }
        }

        composable<ScanQRResult> {
            val qrResult = it.toRoute<ScanQRResult>()
            ScanQRResultsScreen(result = qrResult.result, navController = navController)
        }

        composable<SaveSizesScreen> {
            MeasurementViewPager(navController = navController)
        }
    }
}

@Serializable
object LoginScreen

@Serializable
object RegisterScreen

@Serializable
object HomeScreen

@Serializable
object ScanQRScreen

@Serializable
data class ScanQRResult(val result: String)

@Serializable
object SaveSizesScreen