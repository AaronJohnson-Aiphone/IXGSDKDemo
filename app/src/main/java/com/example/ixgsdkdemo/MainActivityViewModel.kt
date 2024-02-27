package com.example.ixgsdkdemo

import androidx.lifecycle.ViewModel
import com.example.ixgcore.RegistrationManager
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class MainActivityViewModel(
    private val registrationManager: RegistrationManager
): ViewModel() {

    suspend fun sendQRCode(code: String){
            registrationManager.sendQRCode(code)
    }
}