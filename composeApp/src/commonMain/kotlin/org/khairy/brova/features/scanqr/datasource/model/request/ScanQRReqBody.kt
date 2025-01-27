package org.khairy.brova.features.scanqr.datasource.model.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/2/2025 8:33 PM
 */
@Serializable
data class ScanQRReqBody(
    val qr: String
) {
}