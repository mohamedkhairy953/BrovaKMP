package org.khairy.brova.features.scanhistory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.khairy.brova.features.scanhistory.datasource.ScanHistoryRepository

sealed class ScanHistoryEvent {
    object LoadHistory : ScanHistoryEvent()
}

sealed class ScanHistoryState {
    object Idle : ScanHistoryState()
    object Loading : ScanHistoryState()
    data class Success(val history: List<ScanHistoryApiModel.Data>) : ScanHistoryState()
    data class Error(val message: String) : ScanHistoryState()
}

class ScanHistoryViewModel(
    private val repository: ScanHistoryRepository
) : ViewModel() {

    private val _history = MutableStateFlow<List<ScanHistoryApiModel.Data>>(emptyList())
    val history: StateFlow<List<ScanHistoryApiModel.Data>> get() = _history

    private val _state = MutableStateFlow<ScanHistoryState>(ScanHistoryState.Idle)
    val state: StateFlow<ScanHistoryState> get() = _state

    fun onEvent(event: ScanHistoryEvent) {
        when (event) {
            ScanHistoryEvent.LoadHistory -> loadHistory()
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _state.value = ScanHistoryState.Loading
            runCatching {
                repository.getScanHistory()
            }.onSuccess { result ->
                result.getOrNull()?.let { response ->
                    if (response.success) {
                        _history.value = response.data ?: emptyList()
                        _state.value = ScanHistoryState.Success(_history.value)
                    } else {
                        _state.value = ScanHistoryState.Error(response.message ?: "Unknown error")
                    }
                }
            }.onFailure {
                _state.value = ScanHistoryState.Error(it.message ?: "Failed to load history")
            }
        }
    }
} 