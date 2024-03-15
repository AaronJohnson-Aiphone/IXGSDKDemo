package com.example.ixgcore

interface IRegistrationManager {
    suspend fun sendQRCode(qrCode: String): Result<Nothing?>
    suspend fun register(appName: String): Result<Nothing?>
    suspend fun rename(appName: String): Result<Nothing?>
    suspend fun deregister(): Result<Nothing?>
    suspend fun getStatus(): Result<Nothing?>
}