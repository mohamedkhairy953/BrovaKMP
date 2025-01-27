package org.khairy.brova.features.login.di

import org.khairy.brova.features.login.viewmodel.ProductDetailsViewModel
import org.khairy.brova.features.login.viewmodel.RegisterViewModel
import org.khairy.brova.features.register.datasource.RegisterApi
import org.khairy.brova.features.register.datasource.RegisterRepository
import org.khairy.brova.features.register.datasource.RegisterRepositoryImpl
import org.khairy.brova.features.scanqr.datasource.ScanQRApi
import org.khairy.brova.features.scanqr.datasource.ScanQRRepository
import org.khairy.brova.features.scanqr.datasource.ScanQRRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:46 PM
 */

val scanQRModule = module {

    single<ScanQRApi> { ScanQRApi(get()) }
    single<ScanQRRepository> { ScanQRRepositoryImpl(get()) }
    viewModel { ProductDetailsViewModel(get()) }
}