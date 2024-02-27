package com.example.ixgsdkdemo

import androidx.lifecycle.ViewModel
import com.example.ixgcore.RegistrationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val registrationManager: RegistrationManager
): ViewModel() {

    suspend fun sendQRCode(code: String){
            registrationManager.sendQRCode(code)
    }
}