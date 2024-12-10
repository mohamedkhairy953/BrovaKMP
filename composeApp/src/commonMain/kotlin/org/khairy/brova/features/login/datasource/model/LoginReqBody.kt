package org.khairy.brova.features.login.datasource.model

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
data class LoginReqBody(
    val email: String,
    val password: String,
)