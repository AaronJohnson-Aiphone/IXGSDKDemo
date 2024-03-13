package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.ACLAppData
import com.example.ixgcore.api.DeregisterRequestData
import com.example.ixgcore.api.DeregisterRequestDataWrapper
import com.example.ixgcore.api.DeregisterResponseData
import com.example.ixgcore.api.IXGAPIConstants
import com.example.ixgcore.api.IXGCoreModule
import com.example.ixgcore.api.QRRequestData
import com.example.ixgcore.api.QRRequestWrapper
import com.example.ixgcore.api.QRResponseData
import com.example.ixgcore.api.RegisterRequestData
import com.example.ixgcore.api.RegisterRequestDataWrapper
import com.example.ixgcore.api.RegisterResponseData
import com.example.ixgcore.api.RenameRequestData
import com.example.ixgcore.api.RenameRequestDataWrapper
import com.example.ixgcore.api.RenameResponseData
import com.example.ixgcore.api.StatusRequestData
import com.example.ixgcore.api.StatusRequestDataWrapper
import com.example.ixgcore.api.StatusResponseData
import com.squareup.moshi.Moshi

class RegistrationManager: IRegistrationManager {
    private val constants = IXGAPIConstants()
    private val apiService = IXGCoreModule().retrofitServiceACL
    private val dataStore = IXGCoreModule().retrofitDataStoreACL

    override suspend fun sendQRCode(qrCode: String): Result<String> {
        val qrData = QRRequestData(roomCode = qrCode, sid = constants.getSidFromDate(), sys = constants.sys, sysver = constants.sysver)
        val qrWrapper = QRRequestWrapper(qrData)
        val response = apiService.sendQRCode(qrWrapper)
        Log.d("RegistrationManager", "send QR code: ${response.code()}")
        Log.d("RegistrationManager", "send QR body: ${response.body()}")

        when(response.body()){
            null -> {
                Log.d("RegistrationManager", "send QR body is null")
                return Result.failure(Exception("Failed to send QR code"))
            }
            else -> {
                return if(response.isSuccessful) {
                    val moshi = Moshi.Builder().build()
                    val jsonAdapter = moshi.adapter(QRResponseData::class.java)
                    val qrResponseData: QRResponseData? = jsonAdapter.fromJson(response.body()!!)

                    val selectedAppSlot = findFirstVacantAppSlot(qrResponseData!!.apps)
                        ?: return Result.failure(Exception("No open app slots"))

                    val appInfo = IXGAppInfo(name = selectedAppSlot.name, propertyId = qrResponseData.propertyId.toInt(), qrCode = qrCode, appSlotID = selectedAppSlot.clientID)

                    dataStore.setQRCode(qrCode)
                    dataStore.setIXGAppInfo(appInfo)

                    Result.success(appInfo.name)
                }
                else{// if not in 200 range
                    // TODO handle specific error codes
                    // TODO handle specific body error messages
                    Result.failure(Exception("${response.body()}"))
                }
            }
        }
    }

    override suspend fun register(appName: String?): Result<Nothing?> {
        if(!appName.isNullOrEmpty()){ // if they provided a name for the app
            val renameResult = rename(appName)
            if(renameResult.isFailure){
                return Result.failure(Exception(renameResult.exceptionOrNull()))
            }
        }

        val registerData = RegisterRequestData()//TODO fill in
        val registerDataWrapper = RegisterRequestDataWrapper(registerData)
        val response = apiService.regAppClient(registerDataWrapper)
        when(response.body()){
            null -> {
                Log.d("RegistrationManager", "register body is null")
                return Result.failure(Exception("Failed to register app"))
            }
            else -> {
                return if(response.isSuccessful) {
                    val moshi = Moshi.Builder().build()
                    val jsonAdapter = moshi.adapter(RegisterResponseData::class.java)
                    val registerResponseData: RegisterResponseData? = jsonAdapter.fromJson(response.body()!!)

                    //TODO handle response data

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes
                    // TODO handle specific body error messages
                    Result.failure(Exception("${response.body()}"))
                }
            }
        }
    }

    override suspend fun rename(appName: String): Result<Nothing?> {
        val renameData = RenameRequestData()//TODO fill in
        val renameDataWrapper = RenameRequestDataWrapper(renameData)
        val response = apiService.renameAppClient(renameDataWrapper)
        when(response.body()){
            null -> {
                Log.d("RegistrationManager", "rename body is null")
                return Result.failure(Exception("Failed to rename app"))
            }
            else -> {
                return if(response.isSuccessful) {
                    //TODO handle saving new name

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes

                    val moshi = Moshi.Builder().build()
                    val jsonAdapter = moshi.adapter(RenameResponseData::class.java)
                    val renameResponseData: RenameResponseData? = jsonAdapter.fromJson(response.body()!!)
                    Log.d("RegistrationManager", "${renameResponseData?.message}")
                    Result.failure(Exception("${response.body()}"))
                }
            }
        }
    }

    override suspend fun deregister(): Result<Nothing?> {
        val deregisterData = DeregisterRequestData()//TODO fill in
        val deregisterDataWrapper = DeregisterRequestDataWrapper(deregisterData)
        val response = apiService.unregAppClient(deregisterDataWrapper)
        when(response.body()){
            null -> {
                Log.d("RegistrationManager", "deregister body is null")
                return Result.failure(Exception("Failed to deregister app"))
            }
            else -> {
                return if(response.isSuccessful) {
                    val moshi = Moshi.Builder().build()
                    val jsonAdapter = moshi.adapter(DeregisterResponseData::class.java)
                    val deregisterResponseData: DeregisterResponseData? = jsonAdapter.fromJson(response.body()!!)

                    //TODO handle response data

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes
                    // TODO handle specific body error messages
                    Result.failure(Exception("${response.body()}"))
                }
            }
        }
    }

    override suspend fun getStatus(): Result<Nothing?> {
        val statusData = StatusRequestData()//TODO fill in
        val statusDataWrapper = StatusRequestDataWrapper(statusData)
        val response = apiService.checkConsentRoom(statusDataWrapper)
        when(response.body()){
            null -> {
                Log.d("RegistrationManager", "get status body is null")
                return Result.failure(Exception("Failed to get status"))
            }
            else -> {
                return if(response.isSuccessful) {
                    val moshi = Moshi.Builder().build()
                    val jsonAdapter = moshi.adapter(StatusResponseData::class.java)
                    val statusResponseData: StatusResponseData? = jsonAdapter.fromJson(response.body()!!)

                    //TODO handle response data

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes
                    // TODO handle specific body error messages
                    Result.failure(Exception("${response.body()}"))
                }
            }
        }
    }

    private fun findFirstVacantAppSlot(apps: List<ACLAppData>): ACLApp? {
        for(app in apps) {
            if(app.registrationStatus == 1) {
                ACLApp(clientID = app.clientId.toInt(), number = app.number.toInt(), name = app.names.first(), registrationStatus = app.registrationStatus)
            }
        }
        return null
    }
}