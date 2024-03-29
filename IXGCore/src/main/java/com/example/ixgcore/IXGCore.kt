package com.example.ixgcore

import android.content.Context
import com.example.ixgcore.api.Constants
import com.example.ixgcore.api.IStationsManager
import com.example.ixgcore.api.Module
import com.example.ixgcore.api.StationsManager
import com.example.ixgcore.datastore.DataStore
import kotlinx.coroutines.runBlocking

class IXGCore(applicationContext: Context): IIXGCore {
    private val dataStore = DataStore(applicationContext)
    private val apiService = Module().retrofitServiceACL
    private val constants = Constants()

    override val registrationManager: IRegistrationManager = RegistrationManager(dataStore, apiService, constants)
    override val stationsManager: IStationsManager = StationsManager(dataStore, apiService, constants)

    init {//checks if user thinks they are registered, and if so, checks if they are actually registered
        runBlocking {
            if (dataStore.getRegistrationCode().isNotEmpty()) {
                val registrationStatus = registrationManager.getStatus()
                if (registrationStatus.isFailure) {
                    dataStore.cleanUp()
                    throw Exception("No longer registered")
                }
            }
        }
    }

}