package org.khairy.brova.qr

import android.app.Activity
import com.google.zxing.integration.android.IntentIntegrator

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/24/2024 7:38 PM
 */
fun scanQR(activity: Activity) {
    val integrator = IntentIntegrator(activity)
    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
    integrator.setPrompt("Scan a QR Code")
    integrator.setCameraId(0) // Use a specific camera
    integrator.setBeepEnabled(false)
    integrator.setBarcodeImageEnabled(true)
    integrator.initiateScan()

}