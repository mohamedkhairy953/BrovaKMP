package org.khairy.brova.features.scanhistory.di

import org.khairy.brova.features.scanhistory.datasource.ScanHistoryApi
import org.khairy.brova.features.scanhistory.datasource.ScanHistoryRepository
import org.khairy.brova.features.scanhistory.datasource.ScanHistoryRepositoryImpl
import org.khairy.brova.features.scanhistory.viewmodel.ScanHistoryViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val scanHistoryModule = module {
    // API
    singleOf(::ScanHistoryApi)
    
    // Repository
    singleOf(::ScanHistoryRepositoryImpl) bind ScanHistoryRepository::class
    
    // ViewModel
    viewModelOf(::ScanHistoryViewModel)
} 