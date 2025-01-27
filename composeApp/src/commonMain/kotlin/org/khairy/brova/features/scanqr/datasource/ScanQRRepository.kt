package org.khairy.brova.features.scanqr.datasource

import org.khairy.brova.features.scanqr.datasource.model.response.ScanQRApiModel

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:25 PM
 */
interface ScanQRRepository {
    suspend fun fetchDetails(qr: String): Result<ScanQRApiModel>
}