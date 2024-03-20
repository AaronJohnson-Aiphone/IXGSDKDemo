package com.example.ixgcore

import com.example.ixgcore.api.IStationsManager

interface IIXGCore {
    val registrationManager: IRegistrationManager
    val stationsManager: IStationsManager

    suspend fun getStatus(): Result<Nothing?>

}