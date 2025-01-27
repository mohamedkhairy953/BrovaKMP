package org.khairy.brova.features.savesizes.di

import androidx.lifecycle.viewmodel.compose.viewModel
import org.khairy.brova.features.savesizes.viewmodel.TakeMeasuresViewmodel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/27/2025 3:30 PM
 */
val takeMeasuresModule = module {
    viewModel { TakeMeasuresViewmodel() }
}