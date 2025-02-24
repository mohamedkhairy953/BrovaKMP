package org.khairy.brova.features.savesizes.di

import org.khairy.brova.features.savesizes.datasource.SaveSizesApi
import org.khairy.brova.features.savesizes.datasource.SaveSizesRepository
import org.khairy.brova.features.savesizes.datasource.SaveSizesRepositoryImpl
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
    single<SaveSizesApi> { SaveSizesApi(get(), get()) }
    single<SaveSizesRepository> { SaveSizesRepositoryImpl(get(), get()) }
    viewModel { TakeMeasuresViewmodel(get()) }
}