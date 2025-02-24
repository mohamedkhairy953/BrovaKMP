package org.khairy.brova.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import brovakmp.composeapp.generated.resources.Res
import brovakmp.composeapp.generated.resources.ic_brova_banner
import brovakmp.composeapp.generated.resources.ic_notification_on
import brovakmp.composeapp.generated.resources.ic_qr_home
import brovakmp.composeapp.generated.resources.ic_scan_fab
import brovakmp.composeapp.generated.resources.ic_t_shirt
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.khairy.brova.design.AppColors
import org.khairy.brova.features.brovaprefrences.BrovaSharedPrefs
import org.khairy.brova.features.home.model.Measurement
import org.khairy.brova.navigation.ScanQRScreen
import org.khairy.brova.utils.SpacerHeight32
import org.khairy.brova.utils.SpacerHeight8
import org.khairy.brova.utils.SpacerWidth16
import org.koin.compose.koinInject
import org.khairy.brova.features.scanhistory.viewmodel.ScanHistoryViewModel
import org.khairy.brova.features.scanhistory.viewmodel.ScanHistoryEvent
import org.khairy.brova.features.scanhistory.viewmodel.ScanHistoryState
import coil3.compose.AsyncImage

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            UserHeaderSection()
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = AppColors.blue_0072CE,
                modifier = Modifier.size(70.dp),
                onClick = {
                    navController.navigate(ScanQRScreen)
                }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_scan_fab),
                    contentDescription = "Scan",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            HomeScreenContent()
        }
    }
}

@Composable
private fun HomeScreenContent() {
    val viewModel: ScanHistoryViewModel = koinInject()
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.onEvent(ScanHistoryEvent.LoadHistory)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF8F8F8))
    ) {
        WelcomeBanner()
        QRCodeCard()
        
        when (val currentState = state) {
            is ScanHistoryState.Success -> {
                PreviousMeasurementsSection(
                    measurements = currentState.history
                )
            }
            is ScanHistoryState.Error -> {
                // Handle error state
                Text("Error: ${currentState.message}")
            }
            ScanHistoryState.Loading -> {
                // Show loading indicator
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("Loading...")
                }
            }
            else -> {}
        }
    }
}

@Composable
fun UserHeaderSection(sharedPrefs: BrovaSharedPrefs = koinInject()) {
    // Obtain the user data from shared preferences
    val user = sharedPrefs.getUserData()
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Use the user's name if available; otherwise show a default value ("Guest")
        Text(
            text = user?.name ?: "Guest",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            painter = painterResource(Res.drawable.ic_notification_on),
            contentDescription = "User Image",
            tint = AppColors.black_595959,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun WelcomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.25f)
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF0099FF)), // Blue Background
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_brova_banner), // Replace with your welcome image asset
            contentDescription = "Welcome Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun QRCodeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_qr_home),
                contentDescription = "QR Code",
                modifier = Modifier.size(80.dp)
            )
            SpacerWidth16()
            Column {
                Text(
                    text = "وجه كاميرا موبايلك لرمز الQR الموجود في قطعة الملابس عشان تعرف مقاسها وتفاصيلها",
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.border(
                        1.dp,
                        color = AppColors.blue_0072CE,
                        shape = RoundedCornerShape(8.dp)
                    )
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            // Handle QR code scan
                        }.padding(horizontal = 12.dp, vertical = 4.dp),
                    text = "فتح الكاميرا لمسح الرمز",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PreviousMeasurementsSection(measurements: List<ScanHistoryApiModel.Data>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "القياسات السابقة",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Group measurements into rows of two items each
            measurements.chunked(2).forEach { rowMeasurements ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowMeasurements.forEach { measurement ->
                        MeasurementItem(
                            measurement = measurement,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Add spacers for empty slots in the row
                    if (rowMeasurements.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        SpacerHeight32()
        SpacerHeight32()
    }
}

@Composable
fun MeasurementItem(measurement: ScanHistoryApiModel.Data, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f),
        shape = RoundedCornerShape(8.dp),
        elevation = 3.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = measurement.image,
                contentDescription = measurement.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray)
            )
            SpacerHeight8()
            Text(
                text = measurement.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${measurement.price} ${measurement.currency}",
                fontSize = 12.sp,
                color = Color.Gray
            )
            SpacerHeight8()
            Text(
                text = measurement.size,
                fontSize = 12.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = measurement.shop_name,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    HomeScreen(navController = rememberNavController())
}