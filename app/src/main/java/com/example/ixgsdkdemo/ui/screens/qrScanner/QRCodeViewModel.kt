package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class QRCodeViewModel: ViewModel() {
    fun sendQRCode(qrCode: String) {
        viewModelScope.launch {
//            val registrationManager = RegistrationManager()
//            val result = registrationManager.sendQRCode(qrCode)
//            if (result.isFailure) {
//                // Handle error
//            }
        }
    }
}

