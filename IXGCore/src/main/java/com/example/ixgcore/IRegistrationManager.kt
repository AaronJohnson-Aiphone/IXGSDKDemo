package com.example.ixgcore

interface IRegistrationManager {
    suspend fun sendQRCode(qrCode: String): Result<Nothing?>
}