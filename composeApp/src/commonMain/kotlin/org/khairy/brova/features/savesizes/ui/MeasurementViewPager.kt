package org.khairy.brova.features.savesizes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import brovakmp.composeapp.generated.resources.Res
import brovakmp.composeapp.generated.resources.ic_pant_height
import brovakmp.composeapp.generated.resources.ic_pant_thigh
import brovakmp.composeapp.generated.resources.ic_pants_wodth
import brovakmp.composeapp.generated.resources.ic_shirt_height
import brovakmp.composeapp.generated.resources.ic_shirt_width
import brovakmp.composeapp.generated.resources.ic_t_shirt
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.khairy.brova.features.savesizes.viewmodel.TakeMeasuresEvents
import org.khairy.brova.features.savesizes.viewmodel.TakeMeasuresViewmodel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MeasurementViewPager(
    navController: NavHostController,
    takeMeasuresViewmodel: TakeMeasuresViewmodel = koinViewModel(),
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })
    val coroutineScope = rememberCoroutineScope()
    val shirtWidth = takeMeasuresViewmodel.shirtWidth.collectAsState()
    val shirtHeight = takeMeasuresViewmodel.shirtHeight.collectAsState()
    val pantsWidth = takeMeasuresViewmodel.pantsWidth.collectAsState()
    val pantsThigh = takeMeasuresViewmodel.pantsThigh.collectAsState()
    val pantsHeight = takeMeasuresViewmodel.pantsHeight.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                backgroundColor = Color.White,
                elevation = 0.dp,
                //navigationIcon = Res.drawable.baseline_arrow_back_24
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            TabRow(
                tabs = {
                    (0..4).forEach {
                        Divider(
                            color = if (it <= pagerState.currentPage) Color.Green else Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp)
                        )
                    }
                },
                contentColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                selectedTabIndex = pagerState.currentPage,
            )
            HorizontalPager(
                userScrollEnabled = false,
                state = pagerState,
                modifier = Modifier.weight(1f) // Makes the pager fill the screen
            ) { page ->
                when (page) {
                    0 -> TakeMeasuresScreen(
                        image = painterResource(Res.drawable.ic_shirt_height),
                        textFieldLabel = "طول التيشيرت",
                        measurementValue = shirtHeight.value,
                        onMeasurementValueChange = {
                            takeMeasuresViewmodel.onEvent(
                                TakeMeasuresEvents.OnShirtHeightChange(it)
                            )
                        },
                        nextButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    )

                    1 -> TakeMeasuresScreen(
                        image = painterResource(Res.drawable.ic_shirt_width),
                        textFieldLabel = "عرض التيشيرت",
                        measurementValue = shirtWidth.value,
                        onMeasurementValueChange = {
                            takeMeasuresViewmodel.onEvent(
                                TakeMeasuresEvents.OnShirtWidthChange(it)
                            )
                        },
                        nextButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        previousButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )

                    2 -> TakeMeasuresScreen(
                        image = painterResource(Res.drawable.ic_pant_height),
                        textFieldLabel = "طول البنطلون",
                        measurementValue = pantsHeight.value,
                        onMeasurementValueChange = {
                            takeMeasuresViewmodel.onEvent(
                                TakeMeasuresEvents.OnPantsHeightChange(it)
                            )
                        },
                        nextButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        previousButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )

                    3 -> TakeMeasuresScreen(
                        image = painterResource(Res.drawable.ic_pants_wodth),
                        textFieldLabel = "مقاس وسط البنطلون",
                        measurementValue = pantsWidth.value,
                        onMeasurementValueChange = {
                            takeMeasuresViewmodel.onEvent(
                                TakeMeasuresEvents.OnPantsWidthChange(it)
                            )
                        },
                        nextButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        previousButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )

                    4 -> TakeMeasuresScreen(
                        image = painterResource(Res.drawable.ic_pant_thigh),
                        textFieldLabel = "مقاس الفخد",
                        measurementValue = pantsThigh.value,
                        onMeasurementValueChange = {
                            takeMeasuresViewmodel.onEvent(
                                TakeMeasuresEvents.OnPantsThighChange(it)
                            )
                        },
                        nextButtonAction = {},
                        confirmButtonAction = {
                            //navController.navigate("save-sizes")
                        },
                        previousButtonAction = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )
                }
            }
        }
    }
}
