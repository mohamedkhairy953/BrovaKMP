package org.khairy.brova.features.register.datasource.model

import kotlinx.serialization.Serializable


@Serializable
data class RegisterResponseApiModel(
    val data: Data?,
    val message: String?,
    val success: Boolean
) {
    @Serializable
    data class Data(
        val name: String,
        val email: String,
        val id: Int,
        val phone: String,
        val pants_length: Double?,
        val Pants_thigh: Double?,
        val pants_waist: Double?,
        val tshirt_length: Double?,
        val tshirt_width: Double?,
        val status: String?,
        val token: String,
    )
}