package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.Constants
import com.example.ixgcore.api.IService
import com.example.ixgcore.api.data.ACLAppData
import com.example.ixgcore.api.data.DeregisterRequestData
import com.example.ixgcore.api.data.DeregisterRequestDataWrapper
import com.example.ixgcore.api.data.QRRequestData
import com.example.ixgcore.api.data.QRRequestWrapper
import com.example.ixgcore.api.data.QRResponseDataWrapper
import com.example.ixgcore.api.data.RegisterRequestData
import com.example.ixgcore.api.data.RegisterRequestDataWrapper
import com.example.ixgcore.api.data.RegisterResponseDataWrapper
import com.example.ixgcore.api.data.RenameRequestData
import com.example.ixgcore.api.data.RenameRequestDataWrapper
import com.example.ixgcore.api.data.StatusRequestData
import com.example.ixgcore.api.data.StatusRequestDataWrapper
import com.example.ixgcore.api.data.StatusResponseDataWrapper
import com.example.ixgcore.datastore.DataStore
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class RegistrationManager(
    private val dataStore: DataStore,
    private val apiService: IService,
    private val constants: Constants): IRegistrationManager {

    override suspend fun sendQRCode(qrCode: String): Result<Nothing?> {
        val qrData = QRRequestData(roomCode = qrCode, sid = constants.getSidFromDate(), sys = constants.sys, sysver = constants.sysver)
        val qrWrapper = QRRequestWrapper(qrData)
        val response = apiService.sendQRCode(qrWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<QRResponseDataWrapper> = moshi.adapter(QRResponseDataWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "post model conversion: $networkModel")

            val qrResponseData = networkModel!!.qrResponseData

            val selectedAppSlot = findFirstVacantAppSlot(qrResponseData.apps)
                ?: return Result.failure(Exception("No open app slots"))

            val appInfo = IXGAppInfo(name = selectedAppSlot.name, propertyId = qrResponseData.propertyId, qrCode = qrCode, appSlotID = selectedAppSlot.clientID)
            dataStore.setQRCode(qrCode)
            dataStore.setIXGAppInfo(appInfo)
            Result.success(null)

        } else if (response.code() == 410) {
            Result.failure(Exception("TODO: handle 410"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    override suspend fun register(appName: String): Result<Nothing?> {
        val renameResult = rename(appName)
        if(renameResult.isFailure){
            return Result.failure(Exception(renameResult.exceptionOrNull()))
        }

        val registerData = RegisterRequestData(sid = constants.getSidFromDate(), sys = constants.sys, sysver = constants.sysver,
            roomCode = dataStore.getQRCode(),  propertyId = dataStore.getPropertyId(), clientId = dataStore.getAppSlotId(), osKind = constants.osKind)

        val registerDataWrapper = RegisterRequestDataWrapper(registerData)
        val response = apiService.regAppClient(registerDataWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<RegisterResponseDataWrapper> = moshi.adapter(RegisterResponseDataWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "post model conversion: $networkModel")

            val registerResponseData = networkModel!!.registerResponseData

            dataStore.setServerUrl(registerResponseData.url)
            dataStore.setSecretKey(registerResponseData.secretKey)
            dataStore.setCert(registerResponseData.cert)

            Result.success(null)

        } else if (response.code() == 410) {
            Result.failure(Exception("TODO: handle 410"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    override suspend fun rename(appName: String): Result<Nothing?> {
        val renameData = RenameRequestData(roomCode = dataStore.getQRCode(), propertyId = dataStore.getPropertyId(), clientId = dataStore.getAppSlotId(),
            appName = appName, sysver = constants.sysver, sys = constants.sys, sid = constants.getSidFromDate())

        val renameDataWrapper = RenameRequestDataWrapper(renameData)
        val response = apiService.renameAppClient(renameDataWrapper)

        return if (response.isSuccessful) {
            dataStore.setName(appName)
            Result.success(null)

        } else if (response.code() == 410) {
            Result.failure(Exception("TODO: handle 410"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    override suspend fun deregister(): Result<Nothing?> {
        val deregisterData = DeregisterRequestData(roomCode = dataStore.getQRCode(), propertyId = dataStore.getPropertyId(), clientId = dataStore.getAppSlotId(),
            sysver = constants.sysver, sys = constants.sys, sid = constants.getSidFromDate())

        val deregisterDataWrapper = DeregisterRequestDataWrapper(deregisterData)
        val response = apiService.unregAppClient(deregisterDataWrapper)
        return if (response.isSuccessful) {
            dataStore.cleanUp()
            Result.success(null)

        } else if (response.code() == 410) {
            Result.failure(Exception("TODO: handle 410"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    override suspend fun getStatus(): Result<Nothing?> {
        val statusData = StatusRequestData(propertyId = dataStore.getPropertyId(), clientId = dataStore.getAppSlotId(),
            sysver = constants.sysver, sys = constants.sys, sid = constants.getSidFromDate())

        val statusDataWrapper = StatusRequestDataWrapper(statusData)
        val response = apiService.checkConsentRoom(statusDataWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<StatusResponseDataWrapper> = moshi.adapter(StatusResponseDataWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "post model conversion: $networkModel")

            val statusResponseData = networkModel!!.statusResponseData
            //TODO Handle response data

            Result.success(null)

        } else if (response.code() == 410) {
            Result.failure(Exception("TODO: handle 410"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    private fun findFirstVacantAppSlot(apps: List<ACLAppData>): ACLApp? {
        for(app in apps) {
            if(app.registrationStatus == 0)
                return ACLApp(clientID = app.clientID, number = app.number.toInt(), name = app.names.first(), registrationStatus = 0)
        }
        return null
    }
}