package org.khairy.brova.features.savesizes.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ...
 *
 *
 * Copyright (c) 2025 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 1/27/2025 3:17 PM
 */

sealed class TakeMeasuresEvents {
    data class OnShirtWidthChange(val width: String) : TakeMeasuresEvents()
    data class OnShirtHeightChange(val height: String) : TakeMeasuresEvents()
    data class OnPantsWidthChange(val width: String) : TakeMeasuresEvents()
    data class OnPantsThighChange(val thigh: String) : TakeMeasuresEvents()
    data class OnPantsHeightChange(val height: String) : TakeMeasuresEvents()
}

class TakeMeasuresViewmodel() : ViewModel() {

    private val _shirtWidth = MutableStateFlow("")
    val shirtWidth: StateFlow<String> get() = _shirtWidth

    private val _shirtHeight = MutableStateFlow("")
    val shirtHeight: StateFlow<String> get() = _shirtHeight

    private val _pantsWidth = MutableStateFlow("")
    val pantsWidth: StateFlow<String> get() = _pantsWidth

    private val _pantsThigh = MutableStateFlow("")
    val pantsThigh: StateFlow<String> get() = _pantsThigh

    private val _pantsHeight = MutableStateFlow("")
    val pantsHeight: StateFlow<String> get() = _pantsHeight

    fun onEvent(event: TakeMeasuresEvents) {
        when (event) {
            is TakeMeasuresEvents.OnShirtWidthChange -> _shirtWidth.value = event.width
            is TakeMeasuresEvents.OnShirtHeightChange -> _shirtHeight.value = event.height
            is TakeMeasuresEvents.OnPantsWidthChange -> _pantsWidth.value = event.width
            is TakeMeasuresEvents.OnPantsThighChange -> _pantsThigh.value = event.thigh
            is TakeMeasuresEvents.OnPantsHeightChange -> _pantsHeight.value = event.height
        }
    }
}