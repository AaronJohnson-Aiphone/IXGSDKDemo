package com.example.ixgcore

import android.content.Context
import com.example.ixgcore.api.Constants
import com.example.ixgcore.api.Module
import com.example.ixgcore.datastore.DataStore

class IXGCore(applicationContext: Context): IIXGCore {
    private val dataStore = DataStore(applicationContext)
    private val apiService = Module().retrofitServiceACL
    private val constants = Constants()

    override val registrationManager: IRegistrationManager = RegistrationManager(dataStore, apiService, constants)

}