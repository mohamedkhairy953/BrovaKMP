package org.khairy.brova.features.scanqr.datasource.model.response

import kotlinx.serialization.Serializable


@Serializable
data class ScanQRApiModel(
    val data: Data?,
    val message: String?,
    val success: Boolean
) {
    @Serializable
    data class Data(
        val currency: String,
        val description: String,
        val id: Int,
        val image: String,
        val pants_length: Double?,
        val Pants_thigh: Double?,
        val pants_waist: Double?,
        val price: String?,
        val shop_name: String?,
        val size: String?,
        val title: String?,
        val tshirt_length: Double?,
        val tshirt_width: Double?,
        val type: String
    )
}