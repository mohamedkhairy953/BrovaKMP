package org.khairy.brova.features.scanqr.viewmodel

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/1/2025 5:05 PM
 */
sealed class ProductDetailsEvent {
    class ScanQR(val qr: String) : ProductDetailsEvent()
}