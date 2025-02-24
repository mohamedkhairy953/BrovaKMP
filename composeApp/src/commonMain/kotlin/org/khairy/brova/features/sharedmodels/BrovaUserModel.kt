package org.khairy.brova.features.sharedmodels

import kotlinx.serialization.Serializable

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/29/2025 4:44 PM
 */
@Serializable
data class BrovaUserModel(
    val name: String,
    val email: String,
    val id: Int,
    val phone: String,
    val pants_length: Double?,
    val Pants_thigh: Double?,
    val pants_waist: Double?,
    val tshirt_length: Double?,
    val tshirt_width: Double?,
    val status: String?
)
