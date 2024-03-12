package com.example.ixgsdkdemo.ui.screens.registration

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class RegistrationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val roomCode = checkNotNull(savedStateHandle.get<String>("roomCode"))
    var appName = checkNotNull(savedStateHandle.get<String>("appName"))

    fun handleRegistrationClicked(): Boolean {
        register(roomCode)
        return true
    }

    private fun register(roomCode: String) {
    }
}