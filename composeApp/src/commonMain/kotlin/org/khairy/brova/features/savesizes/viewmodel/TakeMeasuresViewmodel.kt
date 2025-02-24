package org.khairy.brova.features.savesizes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.khairy.brova.features.savesizes.datasource.SaveSizesRepository
import org.khairy.brova.features.savesizes.datasource.model.request.SaveSizesReqBody

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
    data object OnSaveClicked : TakeMeasuresEvents()
}

sealed class TakeMeasuresState {
    object Idle : TakeMeasuresState()
    object Loading : TakeMeasuresState()
    object Success : TakeMeasuresState()
    data class Error(val message: String) : TakeMeasuresState()
}

class TakeMeasuresViewmodel(
    private val repo: SaveSizesRepository,
) : ViewModel() {

    private val _shirtWidth = MutableStateFlow<String?>(null)
    val shirtWidth: StateFlow<String?> get() = _shirtWidth

    private val _shirtHeight = MutableStateFlow<String?>(null)
    val shirtHeight: StateFlow<String?> get() = _shirtHeight

    private val _pantsWidth = MutableStateFlow<String?>(null)
    val pantsWidth: StateFlow<String?> get() = _pantsWidth

    private val _pantsThigh = MutableStateFlow<String?>(null)
    val pantsThigh: StateFlow<String?> get() = _pantsThigh

    private val _pantsHeight = MutableStateFlow<String?>(null)
    val pantsHeight: StateFlow<String?> get() = _pantsHeight

    private val _state = MutableStateFlow<TakeMeasuresState>(TakeMeasuresState.Idle)
    val state: StateFlow<TakeMeasuresState> get() = _state

    fun onEvent(event: TakeMeasuresEvents) {
        when (event) {
            is TakeMeasuresEvents.OnShirtWidthChange -> _shirtWidth.value = event.width
            is TakeMeasuresEvents.OnShirtHeightChange -> _shirtHeight.value = event.height
            is TakeMeasuresEvents.OnPantsWidthChange -> _pantsWidth.value = event.width
            is TakeMeasuresEvents.OnPantsThighChange -> _pantsThigh.value = event.thigh
            is TakeMeasuresEvents.OnPantsHeightChange -> _pantsHeight.value = event.height
            TakeMeasuresEvents.OnSaveClicked -> {
                saveSizes()
            }
        }
    }

    fun saveSizes() {
        viewModelScope.launch {
            _state.value = TakeMeasuresState.Loading
            runCatching {
                val reqBody = SaveSizesReqBody(
                    tshirt_width = _shirtWidth.value?.toDoubleOrNull(),
                    tshirt_length = _shirtHeight.value?.toDoubleOrNull(),
                    pants_waist = _pantsWidth.value?.toDoubleOrNull(),
                    pants_length = _pantsThigh.value?.toDoubleOrNull(),
                    Pants_thigh = _pantsHeight.value?.toDoubleOrNull()
                )
                repo.saveUserSizes(reqBody)
            }.onSuccess {
                _state.value = TakeMeasuresState.Success
            }.onFailure {
                _state.value = TakeMeasuresState.Error(it.message ?: "An error occurred")
            }
        }
    }
}