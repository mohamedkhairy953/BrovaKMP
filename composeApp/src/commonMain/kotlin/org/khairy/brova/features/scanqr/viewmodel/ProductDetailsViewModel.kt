package org.khairy.brova.features.login.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.khairy.brova.features.scanqr.datasource.ScanQRRepository
import org.khairy.brova.features.scanqr.viewmodel.ProductDetailsEvent
import org.khairy.brova.features.scanqr.viewmodel.ScanQRUiState

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:08 PM
 */

class ProductDetailsViewModel(
    private val repo: ScanQRRepository,
) : ViewModel() {
    private val _scanQRUiState = MutableStateFlow<ScanQRUiState>(ScanQRUiState.Idle)
    val scanQRUiState: StateFlow<ScanQRUiState> get() = _scanQRUiState

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.ScanQR -> scanQr(event.qr)
            else -> {}
        }
    }

    fun fetchDetails(qr: String) {

    }

    private fun scanQr(qr: String) {
        viewModelScope.launch {
            runCatching {
                _scanQRUiState.value = ScanQRUiState.Loading
                withContext(Dispatchers.IO) { repo.fetchDetails(qr) }
            }.onSuccess {
                it.getOrNull()?.let { respModel ->
                    _scanQRUiState.value = ScanQRUiState.Success(respModel)
                }
            }.onFailure {
                _scanQRUiState.value = ScanQRUiState.Error(it.message ?: "An error occurred")
            }
        }
    }

}