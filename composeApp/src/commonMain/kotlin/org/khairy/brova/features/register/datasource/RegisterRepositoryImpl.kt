package org.khairy.brova.features.register.datasource

import org.khairy.brova.features.brovaprefrences.BrovaSharedPrefs
import org.khairy.brova.features.register.datasource.model.RegisterReqBody
import org.khairy.brova.features.register.mappers.toBrovaUserModel

class RegisterRepositoryImpl(val apiClient: RegisterApi, val brovaPrefs: BrovaSharedPrefs) :
    RegisterRepository {
    override suspend fun register(reqBody: RegisterReqBody): Result<Boolean> {
        return try {
            val response = apiClient.invoke(reqBody)
            if (response.success && response.data != null) {
                response.toBrovaUserModel()?.let {
                    brovaPrefs.saveAuthToken(response.data.token)
                    brovaPrefs.saveUserData(it)
                    Result.success(true)
                } ?: run {
                    Result.failure(Exception("Register failed:"))
                }
            } else {
                Result.failure(Exception("Register failed: ${response.message}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}