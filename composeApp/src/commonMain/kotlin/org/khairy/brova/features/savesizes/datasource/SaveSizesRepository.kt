package org.khairy.brova.features.savesizes.datasource

import org.khairy.brova.features.savesizes.datasource.model.request.SaveSizesReqBody
import org.khairy.brova.features.savesizes.datasource.model.response.SaveSizesApiModel
import org.khairy.brova.features.scanqr.datasource.model.response.ScanQRApiModel

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:25 PM
 */
interface SaveSizesRepository {
    suspend fun saveUserSizes(reqBody: SaveSizesReqBody): Result<SaveSizesApiModel>
}