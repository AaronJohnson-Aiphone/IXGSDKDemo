package com.example.ixgsdkdemo.ui.screens.qrScanner

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ixgcore.RegistrationManager
import kotlinx.coroutines.launch

sealed interface QRCodeScreenState {
    object Scanning: QRCodeScreenState
    object Loading: QRCodeScreenState
    object Finished: QRCodeScreenState
    object Error: QRCodeScreenState
}

class QRCodeViewModel(
    private val registrationManager: RegistrationManager
): ViewModel() {
    var uiState: QRCodeScreenState by mutableStateOf(QRCodeScreenState.Scanning)
        private set

//    private val _qrCode = MutableStateFlow("")
//    val qrCode: StateFlow<String>
//        get() = _qrCode
//    fun updateQrCode(code: String) {
//        _qrCode.value = code
//    }
    fun sendQRCode(qrCode: String) {
        viewModelScope.launch {
//            val result = registrationManager.sendQRCode(qrCode)
//            if (result.isSuccess) {
//                uiState = QRCodeScreenState.Finished
//            } else if (result.isFailure) {
//                uiState = QRCodeScreenState.Error
//            }
            uiState = QRCodeScreenState.Finished
        }
    }

//    companion object {
//        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//                return QRCodeViewModel(
//                    registrationManager = RegistrationManager()
//                ) as T
//            }
//        }
//    }
}

