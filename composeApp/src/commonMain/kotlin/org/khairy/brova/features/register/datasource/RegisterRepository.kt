package org.khairy.brova.features.register.datasource

import org.khairy.brova.features.register.datasource.model.RegisterReqBody

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:25 PM
 */
interface RegisterRepository {
    suspend fun register(reqBody: RegisterReqBody): Result<Boolean>
}