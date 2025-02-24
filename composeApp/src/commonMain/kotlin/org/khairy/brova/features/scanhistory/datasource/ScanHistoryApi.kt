package org.khairy.brova.features.scanhistory.datasource

import ScanHistoryApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.khairy.brova.features.brovaprefrences.BrovaSharedPrefs

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/1/2025 6:51 PM
 */
class ScanHistoryApi(private val httpClient: HttpClient, val brovaSharedPrefs: BrovaSharedPrefs) {
    val endpoint = "scan/history"
    suspend fun invoke(): ScanHistoryApiModel {
        val response: HttpResponse = httpClient.get(endpoint) {
            contentType(ContentType.Application.Json)
            header("Authorization", brovaSharedPrefs.getAuthToken())
            println(brovaSharedPrefs.getAuthToken())
        }

        return response.body()
    }
}