package org.khairy.brova.features.login.di

import org.khairy.brova.features.login.datasource.LoginApi
import org.khairy.brova.features.login.datasource.LoginRepository
import org.khairy.brova.features.login.datasource.LoginRepositoryImpl
import org.khairy.brova.features.login.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
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

val loginModule = module {

    single<LoginApi> { LoginApi(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    viewModel { LoginViewModel(get()) }
}