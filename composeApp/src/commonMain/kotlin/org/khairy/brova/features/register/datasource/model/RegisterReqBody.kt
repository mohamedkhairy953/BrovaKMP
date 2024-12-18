package org.khairy.brova.features.register.datasource.model

import kotlinx.serialization.Serializable

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:54 PM
 */
@Serializable
data class RegisterReqBody(
    val email: String,
    val name: String,
    val password: String,
    val confirm_password: String,
    val phone: String,
)