package org.khairy.brova.features.savesizes.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.khairy.brova.features.savesizes.datasource.model.request.SaveSizesReqBody
import org.khairy.brova.features.savesizes.datasource.model.response.SaveSizesApiModel
import org.khairy.brova.features.scanqr.datasource.model.request.ScanQRReqBody
import org.khairy.brova.features.scanqr.datasource.model.response.ScanQRApiModel

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/1/2025 6:51 PM
 */
class SaveSizesApi(private val httpClient: HttpClient) {
    val endpoint = "save-sizes"
    suspend fun invoke(reqBody: SaveSizesReqBody): SaveSizesApiModel {
        val response: HttpResponse = httpClient.post(endpoint) {
            contentType(ContentType.Application.Json)
            setBody(reqBody)
            header("Authorization", "Bearer 89|FdK9FGFCPWNs1BEHHpapBvJOXVf3UWoGlkLjelWo669a0493")
        }

        return response.body()
    }
}