package org.khairy.brova.features.savesizes.datasource.model.request

import kotlinx.serialization.Serializable

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/13/2025 6:42 AM
 */
@Serializable
data class SaveSizesReqBody(
    val pants_length: Double?,
    val Pants_thigh: Double?,
    val pants_waist: Double?,
    val tshirt_length: Double?,
    val tshirt_width: Double?
)
