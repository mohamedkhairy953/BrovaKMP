package org.khairy.brova.features.login.datasource

import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.khairy.brova.features.login.datasource.model.LoginReqBody
import org.khairy.brova.utils.BASE_URL

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:51 PM
 */
class LoginApi(private val httpClient: HttpClient) {
    val endpoint = "auth/login"
    suspend fun invoke(reqBody: LoginReqBody): HttpResponse {
        val response: HttpResponse = httpClient.post(endpoint) {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            setBody(reqBody)
        }
        return response
    }
}