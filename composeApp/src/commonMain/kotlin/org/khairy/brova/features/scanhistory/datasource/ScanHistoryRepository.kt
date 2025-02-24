package org.khairy.brova.features.scanhistory.datasource

import ScanHistoryApiModel

interface ScanHistoryRepository {
    suspend fun getScanHistory(): Result<ScanHistoryApiModel>
} 