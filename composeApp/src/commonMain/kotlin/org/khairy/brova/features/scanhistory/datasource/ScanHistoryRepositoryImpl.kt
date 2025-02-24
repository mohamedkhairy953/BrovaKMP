package org.khairy.brova.features.scanhistory.datasource

import ScanHistoryApiModel

class ScanHistoryRepositoryImpl(
    private val scanHistoryApi: ScanHistoryApi
) : ScanHistoryRepository {
    
    override suspend fun getScanHistory(): Result<ScanHistoryApiModel> {
        return runCatching {
            scanHistoryApi.invoke()
        }.fold(
            onSuccess = { response ->
                if (response.success) {
                    Result.success(response)
                } else {
                    Result.failure(Exception("Failed to get scan history: ${response.message}"))
                }
            },
            onFailure = { e ->
                Result.failure(e)
            }
        )
    }
} 