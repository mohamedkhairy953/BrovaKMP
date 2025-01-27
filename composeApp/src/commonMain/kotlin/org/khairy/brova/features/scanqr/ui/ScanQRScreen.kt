package org.khairy.brova.features.scanqr.ui

import androidx.compose.runtime.Composable
import org.publicvalue.multiplatform.qrcode.CodeType
import org.publicvalue.multiplatform.qrcode.ScannerWithPermissions

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/25/2024 6:22 PM
 */

@Composable
fun ScanQRScreen(onScanned: (String) -> Unit) {
    ScannerWithPermissions(
        onScanned = { onScanned(it); true },
        types = listOf(CodeType.QR)
    )

}