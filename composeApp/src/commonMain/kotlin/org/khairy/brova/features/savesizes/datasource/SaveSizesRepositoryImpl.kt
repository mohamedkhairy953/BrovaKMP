package org.khairy.brova.features.savesizes.datasource

import org.khairy.brova.features.brovaprefrences.BrovaSharedPrefs
import org.khairy.brova.features.savesizes.datasource.model.request.SaveSizesReqBody
import org.khairy.brova.features.savesizes.datasource.model.response.SaveSizesApiModel

class SaveSizesRepositoryImpl(
    private val saveSizesApi: SaveSizesApi,
    private val brovaSharedPrefs: BrovaSharedPrefs
) : SaveSizesRepository {
    override suspend fun saveUserSizes(reqBody: SaveSizesReqBody): Result<SaveSizesApiModel> {
        runCatching {
            saveSizesApi.invoke(reqBody)
        }.onSuccess { response ->
            return if (response.success && response.data != null) {
                updateUserSizes(response.data)
                Result.success(response)
            } else {
                Result.failure(Exception("Failed to save user sizes: ${response.message}"))
            }
        }.onFailure { e ->
            return Result.failure(e)
        }
        return Result.failure(Exception("Failed to save user sizes"))
    }

    private fun updateUserSizes(data: SaveSizesApiModel.Data) {
        val brovaUserModel = brovaSharedPrefs.getUserData()
        val userModelAfterUpdatingSizes = brovaUserModel?.copy(
            pants_length = data.pants_length,
            Pants_thigh = data.Pants_thigh,
            pants_waist = data.pants_waist,
            tshirt_length = data.tshirt_length,
            tshirt_width = data.tshirt_width
        )
        userModelAfterUpdatingSizes?.let { brovaSharedPrefs.saveUserData(it) }
    }
}