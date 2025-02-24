package org.khairy.brova.features.login.di

import org.khairy.brova.features.login.viewmodel.RegisterViewModel
import org.khairy.brova.features.register.datasource.RegisterApi
import org.khairy.brova.features.register.datasource.RegisterRepository
import org.khairy.brova.features.register.datasource.RegisterRepositoryImpl
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

val registerModule = module {

    single<RegisterApi> { RegisterApi(get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get(), get()) }
    viewModel { RegisterViewModel(get()) }
}