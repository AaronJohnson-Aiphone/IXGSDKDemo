package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ixgsdkdemo.network.MyApi
import com.example.ixgsdkdemo.network.RequestData
import com.example.ixgsdkdemo.network.RequestWrapper
import kotlinx.coroutines.launch

sealed interface QRScannerUiState {
    object Loading: QRScannerUiState

    data class Success(val photos: String): QRScannerUiState
    data class Error(val errorMessage: String): QRScannerUiState
}

class QRCodeViewModel: ViewModel() {
    var qrUiState: QRScannerUiState by mutableStateOf(QRScannerUiState.Loading)
        private set

    init {
        sendQRCode()
    }

    private fun sendQRCode() {
        viewModelScope.launch {
            qrUiState = try {
                val data = RequestData(roomCode = "1234", sid = "20240213012935940000", sys = "3", sysver = "1.2")
                val wrapper = RequestWrapper(data)
                val result = MyApi.retrofitServiceACL.sendQRCode(wrapper)
                QRScannerUiState.Success(result.message)
            } catch (e: Exception) {
                QRScannerUiState.Error(e.localizedMessage!!)
            }
        }
    }

}

