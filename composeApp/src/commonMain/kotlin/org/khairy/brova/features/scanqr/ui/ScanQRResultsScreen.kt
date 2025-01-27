package org.khairy.brova.features.scanqr.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import brovakmp.composeapp.generated.resources.Res
import brovakmp.composeapp.generated.resources.ic_t_shirt
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.khairy.brova.features.login.viewmodel.ProductDetailsViewModel
import org.khairy.brova.features.scanqr.datasource.model.response.ScanQRApiModel
import org.khairy.brova.features.scanqr.viewmodel.ProductDetailsEvent
import org.khairy.brova.features.scanqr.viewmodel.ScanQRUiState
import org.khairy.brova.navigation.SaveSizesScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ScanQRResultsScreen(
    result: String,
    viewModel: ProductDetailsViewModel = koinViewModel(),
    navController: NavHostController,
) {

    LaunchedEffect(result) {
        println("Scanning QR: $result")
        viewModel.onEvent(ProductDetailsEvent.ScanQR(result))
    }
    val scanQRUiStateState = viewModel.scanQRUiState.collectAsState()
    when (val uiState = scanQRUiStateState.value) {
        is ScanQRUiState.Success -> {
            println("Success: ${uiState.apiModel}")
            ProductDetailsContent(
                model = uiState.apiModel,
                onRescanClicked = {
                    navController.navigate(SaveSizesScreen)
                },
            )
        }

        is ScanQRUiState.Error -> {
            println("Error: ${uiState.message}")
            Snackbar {
                Text(text = uiState.message)
            }
        }

        else -> {
            println("Loading")
            // Show the loading state
        }
    }

}

@Composable
private fun ProductDetailsContent(
    model: ScanQRApiModel,
    onRescanClicked: () -> Unit = {},
    onBackHomeClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "نتائج البحث",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Product Info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = model.data?.size ?: "null",
                            color = Color.White,
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = model.data?.title ?: "null",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = model.data?.price ?: "null",
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product Image
                AsyncImage(
                    model = model.data?.image
                        ?: "", // Replace with the actual image URL from the API response
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Available Sizes
                Text(
                    text = "المقاسات المتاحة",
                    style = MaterialTheme.typography.caption
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    listOf("XL", "L", "M", "S").forEach { size ->
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = size, style = MaterialTheme.typography.caption)
                        }
                    }
                }

                // Available Colors
                Text(
                    text = "الألوان المتاحة",
                    style = MaterialTheme.typography.caption
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    listOf(Color.Blue, Color.Green, Color.Black, Color.White).forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(color, shape = CircleShape)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Suggestion Card
        Card(
            elevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_t_shirt), // Replace with your image resource
                    contentDescription = "Suggestion Icon",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "التشيرت مناسب لمقاساتك",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "بننصحك تشتريه",
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onBackHomeClicked,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
            ) {
                Text(text = "رجوع للرئيسية")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onRescanClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "كمل بحث")
            }
        }
    }
}