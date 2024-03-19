package com.example.ixgsdkdemo.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ixgcore.IRegistrationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed interface RegistrationUiState {
    object DataEntry
    object Loading
    object Finished
}


class RegistrationViewModel(
    private val registrationManager: IRegistrationManager,
) : ViewModel() {

    var appName: MutableStateFlow<String> = MutableStateFlow("My Intercom App")
    var registerResult: MutableStateFlow<Result<Nothing?>?> = MutableStateFlow(null)

    fun handleRegistrationClicked(): Boolean {
        register()
        return true
    }

    private fun register() {
        viewModelScope.launch {
            registerResult.value = registrationManager.register(appName = appName.value)
        }
    }
}