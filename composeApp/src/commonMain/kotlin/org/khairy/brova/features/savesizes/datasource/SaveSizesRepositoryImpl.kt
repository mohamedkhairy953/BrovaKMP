package org.khairy.brova.features.savesizes.datasource

import org.khairy.brova.features.savesizes.datasource.model.request.SaveSizesReqBody
import org.khairy.brova.features.savesizes.datasource.model.response.SaveSizesApiModel

class SaveSizesRepositoryImpl(
    private val saveSizesApi: SaveSizesApi
) : SaveSizesRepository {
    override suspend fun saveUserSizes(reqBody: SaveSizesReqBody): Result<SaveSizesApiModel> {
        runCatching {
            saveSizesApi.invoke(reqBody)
        }.onSuccess { response ->
            return if (response.success) {
                Result.success(response)
            } else {
                Result.failure(Exception("Failed to save user sizes: ${response.message}"))
            }
        }.onFailure { e ->
            return Result.failure(e)
        }
        return Result.failure(Exception("Failed to save user sizes"))
    }
}