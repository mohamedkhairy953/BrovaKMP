package org.khairy.brova.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/12/2025 3:24 PM
 */
@Serializable
open class BrovaBaseApiModel<T>(
    val message: String,
    val success: Boolean,
    val data: T
)