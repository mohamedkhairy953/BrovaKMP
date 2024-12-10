package org.khairy.brova.features.login.datasource

import io.ktor.http.HttpStatusCode
import org.khairy.brova.features.login.datasource.model.LoginReqBody

class LoginRepositoryImpl(val loginApi: LoginApi) : LoginRepository {

    override suspend fun login(username: String, password: String): Result<Boolean> {
        return try {
            val response = loginApi.invoke(LoginReqBody(username, password))
            if (response.status == HttpStatusCode.OK) {
                Result.success(true)
            } else {
                Result.failure(Exception("Login failed: ${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}