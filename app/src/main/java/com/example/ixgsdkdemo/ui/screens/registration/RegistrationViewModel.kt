package com.example.ixgsdkdemo.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ixgcore.RegistrationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationManager: RegistrationManager,
) : ViewModel() {

    var appName: MutableStateFlow<String> = MutableStateFlow("My Intercom App")

    fun handleRegistrationClicked(): Boolean {
        register()
        return true
    }

    private fun register() {
        viewModelScope.launch {
            registrationManager.register(null)
        }
    }
}