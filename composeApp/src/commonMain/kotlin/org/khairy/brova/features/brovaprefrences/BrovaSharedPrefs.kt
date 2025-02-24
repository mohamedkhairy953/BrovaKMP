package org.khairy.brova.features.brovaprefrences

import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.khairy.brova.features.sharedmodels.BrovaUserModel

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/27/2025 4:50 PM
 */
class BrovaSharedPrefs {
    val settings: Settings = Settings()


    val authTokenKey = "auth_token"
    val userDataKey = "user_data"

    fun saveAuthToken(token: String) {
        settings.putString(authTokenKey, "Bearer $token")
    }

    fun getAuthToken(): String {
        return "Bearer 106|YvdApEVVl1ncqX4OzTA1ovwOloTHgFXnLk7SyeQNed1b4757"
    }

    fun saveUserData(userData: BrovaUserModel) {
        val jsonElement = Json.encodeToJsonElement(userData)
        println("Saving user data as Json $jsonElement")
        settings.putString(userDataKey, jsonElement.toString())
    }

    fun getUserData(): BrovaUserModel? {
        val userDataString = settings.getString(userDataKey, "")
        return BrovaUserModel(
            id = 1,
            name = "Mohamed Sallam",
            email = "mohamed@tawk.to",
            tshirt_width = 0.0,
            tshirt_length = 0.0,
            pants_waist = 0.0,
            Pants_thigh = 0.0,
            pants_length = 0.0,
            status = "active",
            phone = "01000000000"
        )
        if(userDataString.isEmpty()) return null
        return Json.decodeFromJsonElement<BrovaUserModel?>(Json.parseToJsonElement(userDataString))
    }
}