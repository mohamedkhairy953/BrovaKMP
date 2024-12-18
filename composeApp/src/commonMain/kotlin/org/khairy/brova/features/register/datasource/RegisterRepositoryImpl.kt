package org.khairy.brova.features.register.datasource

import io.ktor.http.HttpStatusCode
import org.khairy.brova.features.register.datasource.model.RegisterReqBody

class RegisterRepositoryImpl(val apiClient: RegisterApi) : RegisterRepository {
    override suspend fun register(reqBody: RegisterReqBody): Result<Boolean> {
        return try {
            val response = apiClient.invoke(reqBody)
            if (response.status == HttpStatusCode.OK) {
                Result.success(true)
            } else {
                Result.failure(Exception("Register failed: ${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}