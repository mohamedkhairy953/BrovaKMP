package org.khairy.brova.features.scanqr.viewmodel

import org.khairy.brova.features.scanqr.datasource.model.response.ScanQRApiModel

sealed class ScanQRUiState {
    object Idle : ScanQRUiState()
    object Loading : ScanQRUiState()
    data class Success(val apiModel: ScanQRApiModel) : ScanQRUiState()
    data class Error(val message: String) : ScanQRUiState()
}