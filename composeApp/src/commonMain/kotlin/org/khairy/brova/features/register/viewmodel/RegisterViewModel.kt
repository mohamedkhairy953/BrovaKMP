package org.khairy.brova.features.login.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.khairy.brova.features.register.datasource.RegisterRepository
import org.khairy.brova.features.register.datasource.model.RegisterReqBody

/**
 * ...
 *
 *
 * Copyright (c) 2024 . All rights reserved.
 *
 * @author Mohamed "mohamed" Sallam.
 * @since 12/3/2024 6:08 PM
 */
sealed class RegisterEvent {
    data class OnUsernameChange(val username: String) : RegisterEvent()
    data class OnPasswordChange(val password: String) : RegisterEvent()
    data class OnConfirmPasswordChange(val confirmPassword: String) : RegisterEvent()
    data class OnEmailChange(val email: String) : RegisterEvent()
    data class OnPhoneChange(val phone: String) : RegisterEvent()
    data object OnRegisterClick : RegisterEvent()
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

class RegisterViewModel(
    private val repo: RegisterRepository,
) : ViewModel() {
    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> get() = _state

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> get() = _confirmPassword

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> get() = _phone

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnUsernameChange -> _username.value = event.username
            is RegisterEvent.OnPasswordChange -> _password.value = event.password
            is RegisterEvent.OnConfirmPasswordChange -> _confirmPassword.value =
                event.confirmPassword

            is RegisterEvent.OnEmailChange -> _email.value = event.email
            is RegisterEvent.OnPhoneChange -> _phone.value = event.phone
            RegisterEvent.OnRegisterClick -> register()
            else -> {}
        }
    }

    private fun register() {
        _state.value = RegisterState.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                val reqBody = RegisterReqBody(
                    name = _username.value,
                    password = _password.value,
                    confirm_password = _confirmPassword.value,
                    email = _email.value,
                    phone = _phone.value
                )
                repo.register(reqBody)
            }
            _state.value = result.fold(
                onSuccess = { RegisterState.Success },
                onFailure = { RegisterState.Error(it.message ?: "Unknown error") }
            )
        }
    }
}