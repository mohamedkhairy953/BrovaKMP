package org.khairy.brova.features.login.datasource

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:25 PM
 */
interface LoginRepository {
    suspend fun login(username: String, password: String): Result<Boolean>
}