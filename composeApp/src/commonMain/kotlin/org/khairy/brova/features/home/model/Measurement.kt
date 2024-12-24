package org.khairy.brova.features.home.model

import org.jetbrains.compose.resources.DrawableResource

data class Measurement(
    val name: String,
    val price: String,
    val size: String,
    val imageRes: DrawableResource
)