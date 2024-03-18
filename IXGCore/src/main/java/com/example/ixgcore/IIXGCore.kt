package com.example.ixgcore

import com.example.ixgcore.api.IStationManager

interface IIXGCore {
    val registrationManager: IRegistrationManager
    val stationManager: IStationManager

}