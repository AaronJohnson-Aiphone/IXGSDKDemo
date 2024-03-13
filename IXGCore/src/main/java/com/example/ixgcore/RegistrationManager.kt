package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.Constants
import com.example.ixgcore.api.Module
import com.example.ixgcore.api.data.ACLAppData
import com.example.ixgcore.api.data.DeregisterRequestData
import com.example.ixgcore.api.data.DeregisterRequestDataWrapper
import com.example.ixgcore.api.data.DeregisterResponseData
import com.example.ixgcore.api.data.QRRequestData
import com.example.ixgcore.api.data.QRRequestWrapper
import com.example.ixgcore.api.data.QRResponseData
import com.example.ixgcore.api.data.RegisterRequestData
import com.example.ixgcore.api.data.RegisterRequestDataWrapper
import com.example.ixgcore.api.data.RegisterResponseData
import com.example.ixgcore.api.data.RenameRequestData
import com.example.ixgcore.api.data.RenameRequestDataWrapper
import com.example.ixgcore.api.data.RenameResponseData
import com.example.ixgcore.api.data.StatusRequestData
import com.example.ixgcore.api.data.StatusRequestDataWrapper
import com.example.ixgcore.api.data.StatusResponseData
import com.squareup.moshi.Moshi

class RegistrationManager: IRegistrationManager {
    private val constants = Constants()
    private val apiService = Module().retrofitServiceACL
    private val dataStore = Module().retrofitDataStoreACL

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
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(QRResponseData::class.java)

                return if(response.isSuccessful) {
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

                    val qrResponseData: QRResponseData? = response.errorBody()?.string()
                        ?.let { jsonAdapter.fromJson(it) }
                    Log.d("RegistrationManager", "${qrResponseData?.message}")
                    Result.failure(Exception("${qrResponseData?.message}"))
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
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(RegisterResponseData::class.java)

                return if(response.isSuccessful) {
                    val registerResponseData: RegisterResponseData? = jsonAdapter.fromJson(response.body()!!)

                    //TODO handle response data

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes

                    val registerResponseData: RegisterResponseData? = response.errorBody()?.string()
                        ?.let { jsonAdapter.fromJson(it) }
                    Log.d("RegistrationManager", "${registerResponseData?.message}")
                    Result.failure(Exception("${registerResponseData?.message}"))
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
                    val renameResponseData: RenameResponseData? = response.errorBody()?.string()
                        ?.let { jsonAdapter.fromJson(it) }
                    Log.d("RegistrationManager", "${renameResponseData?.message}")
                    Result.failure(Exception("${renameResponseData?.message}"))
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
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(DeregisterResponseData::class.java)

                return if(response.isSuccessful) {
                    val deregisterResponseData: DeregisterResponseData? = jsonAdapter.fromJson(response.body()!!)

                    //TODO handle response data

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes

                    val deregisterResponseData: DeregisterResponseData? = response.errorBody()?.string()
                        ?.let { jsonAdapter.fromJson(it) }
                    Log.d("RegistrationManager", "${deregisterResponseData?.message}")
                    Result.failure(Exception("${deregisterResponseData?.message}"))
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
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(StatusResponseData::class.java)

                return if(response.isSuccessful) {
                    val statusResponseData: StatusResponseData? = jsonAdapter.fromJson(response.body()!!)

                    //TODO handle response data

                    Result.success(null)
                } else{// if not in 200 range
                    // TODO handle specific error codes

                    val statusResponseData: StatusResponseData? = response.errorBody()?.string()
                        ?.let { jsonAdapter.fromJson(it) }
                    Log.d("RegistrationManager", "${statusResponseData?.message}")
                    Result.failure(Exception("${statusResponseData?.message}"))
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