package org.khairy.brova.features.scanqr.datasource

import org.khairy.brova.features.scanqr.datasource.model.response.ScanQRApiModel

class ScanQRRepositoryImpl(val api: ScanQRApi) : ScanQRRepository {
    override suspend fun fetchDetails(qr: String): Result<ScanQRApiModel> {
        runCatching {
            api.invoke(qr)
        }.onSuccess { response ->
            println("response: $response")
            return if (response.success) {
                Result.success(response)
            } else {
                Result.failure(Exception("Failed to fetch details: ${response.message}"))
            }
        }.onFailure { e ->
            return Result.failure(e)
        }
        return Result.failure(Exception("Failed to fetch details"))
    }
}