package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.Constants
import com.example.ixgcore.api.IService
import com.example.ixgcore.api.data.ACLAppData
import com.example.ixgcore.api.data.CheckStatusRequestData
import com.example.ixgcore.api.data.CheckStatusRequestWrapper
import com.example.ixgcore.api.data.CheckStatusResponseWrapper
import com.example.ixgcore.api.data.DeregisterRequestData
import com.example.ixgcore.api.data.DeregisterRequestWrapper
import com.example.ixgcore.api.data.QRRequestData
import com.example.ixgcore.api.data.QRRequestWrapper
import com.example.ixgcore.api.data.QRResponseWrapper
import com.example.ixgcore.api.data.RegisterRequestData
import com.example.ixgcore.api.data.RegisterRequestWrapper
import com.example.ixgcore.api.data.RegisterResponseWrapper
import com.example.ixgcore.api.data.RenameRequestData
import com.example.ixgcore.api.data.RenameRequestWrapper
import com.example.ixgcore.api.data.SetStatusRequestData
import com.example.ixgcore.api.data.SetStatusRequestWrapper
import com.example.ixgcore.api.data.SetStatusResponseWrapper
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
            val adapter: JsonAdapter<QRResponseWrapper> = moshi.adapter(QRResponseWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "QRResponseData post model conversion: $networkModel")

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

        val registerWrapper = RegisterRequestWrapper(registerData)
        val response = apiService.regAppClient(registerWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<RegisterResponseWrapper> = moshi.adapter(RegisterResponseWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "RegisterResponseData post model conversion: $networkModel")

            val registerResponseData = networkModel!!.registerResponseData

            dataStore.setServerUrl(registerResponseData.url)
            dataStore.setSecretKey(registerResponseData.secretKey)
            dataStore.setCert(registerResponseData.cert)
            dataStore.setRegistrationCode(registerResponseData.registrationCode)

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

        val renameWrapper = RenameRequestWrapper(renameData)
        val response = apiService.renameAppClient(renameWrapper)

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

        val deregisterWrapper = DeregisterRequestWrapper(deregisterData)
        val response = apiService.unregAppClient(deregisterWrapper)
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
        val checkStatusData = CheckStatusRequestData(propertyId = dataStore.getPropertyId(), clientId = dataStore.getAppSlotId(),
            sysver = constants.sysver, sys = constants.sys, sid = constants.getSidFromDate(), registrationCode = dataStore.getRegistrationCode())

        val checkStatusWrapper = CheckStatusRequestWrapper(checkStatusData)
        val response = apiService.checkConsentRoom(checkStatusWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<CheckStatusResponseWrapper> = moshi.adapter(CheckStatusResponseWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "CheckStatusResponseData post model conversion: $networkModel")

            val checkStatusResponseData = networkModel!!.checkStatusResponseData
            //TODO Handle response data

            Result.success(null)

        } else if (response.code() == 410) {
            Result.failure(Exception("No longer registered"))
        } else {
            Result.failure(Exception("Unhandled status code ${response.code()}"))
        }
    }

    private suspend fun setStatus(status: Int): Result<Nothing?> {
        val setStatusData = SetStatusRequestData(propertyId = dataStore.getPropertyId(), clientId = dataStore.getAppSlotId(),
            sysver = constants.sysver, sys = constants.sys, sid = constants.getSidFromDate(), registrationCode = dataStore.getRegistrationCode())

        val setStatusWrapper = SetStatusRequestWrapper(setStatusData)
        val response = apiService.setConsentInfo(setStatusWrapper)

        return if (response.isSuccessful) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val adapter: JsonAdapter<SetStatusResponseWrapper> = moshi.adapter(SetStatusResponseWrapper::class.java)
            val networkModel = adapter.fromJson(response.body()!!)
            Log.d("RegistrationManager", "SetStatusResponseData post model conversion: $networkModel")

            val setStatusResponseData = networkModel!!.setStatusResponseData
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