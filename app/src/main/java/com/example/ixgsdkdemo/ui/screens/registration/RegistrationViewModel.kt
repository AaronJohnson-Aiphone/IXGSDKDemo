package com.example.ixgsdkdemo.ui.screens.registration

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegistrationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val roomCode = checkNotNull(savedStateHandle.get<String>("roomCode"))
    private var seedName = checkNotNull(savedStateHandle.get<String>("appName"))
    var appName: MutableStateFlow<String> = MutableStateFlow(seedName)


    fun updateAppName(name: String) {
        appName.value = name
    }

    fun handleRegistrationClicked(): Boolean {
        register(roomCode)
        return true
    }

    private fun register(roomCode: String) {
    }
}