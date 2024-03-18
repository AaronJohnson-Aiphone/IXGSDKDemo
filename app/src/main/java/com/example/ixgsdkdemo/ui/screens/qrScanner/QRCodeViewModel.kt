package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class QRCodeViewModel: ViewModel() {
    var uiState: QRCodeScreenState by mutableStateOf(QRCodeScreenState.Scanning)
        private set

    private val _qrCode = MutableStateFlow("")
    val qrCode: StateFlow<String>
        get() = _qrCode
    fun updateQrCode(code: String) {
        _qrCode.value = code
    }
    fun sendQRCode(qrCode: String) {
        viewModelScope.launch {
//            val registrationManager = RegistrationManager()
//            val result = registrationManager.sendQRCode(qrCode)
//            if (result.isFailure) {
//                // Handle error
//            }
            uiState = QRCodeScreenState.Finished
        }
    }
}

