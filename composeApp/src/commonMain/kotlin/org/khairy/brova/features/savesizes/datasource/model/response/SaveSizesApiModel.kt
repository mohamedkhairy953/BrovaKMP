package org.khairy.brova.features.savesizes.datasource.model.response

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/13/2025 6:43 AM
 */
data class SaveSizesApiModel(
    val message: String,
    val success: Boolean,
    val data: Data
) {
    data class Data(
        val id: Int,
        val name: String,
        val phone: String,
        val status: String,
        val pants_length: Double,
        val Pants_thigh: Double,
        val pants_waist: Double,
        val tshirt_length: Double,
        val tshirt_width: Double
    )
}