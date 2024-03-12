package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.ACLAppData
import com.example.ixgcore.api.IXGAPIConstants
import com.example.ixgcore.api.IXGCoreModule
import com.example.ixgcore.api.QRRequestData
import com.example.ixgcore.api.QRRequestWrapper

class RegistrationManager: IRegistrationManager {
    private val constants = IXGAPIConstants()
    private val apiService = IXGCoreModule().retrofitServiceACL
    private val dataStore = IXGCoreModule().retrofitDataStoreACL

    override suspend fun sendQRCode(qrCode: String): Result<Nothing?> {//TODO change result to app name if success
        val qrData = QRRequestData(
            roomCode = qrCode,
            sid = constants.getSidFromDate(),
            sys = constants.sys,
            sysver = constants.sysver
        )
        val qrWrapper = QRRequestWrapper(qrData)
        val response = apiService.sendQRCode(qrWrapper)
        Log.d("RegistrationManager", "send QR code: $response")
//        Log.d("RegistrationManager", "send QR code: ${response.code()}")
//        Log.d("RegistrationManager", "send QR body: ${response.body()}")
//        return if (response.isSuccessful) {//TODO refactor to put body out of response
//            when(response.body()) {
//                null -> Result.failure(Exception("Failed to send QR code"))
//                else -> {
//                    val moshi = Moshi.Builder().build()
//                    val jsonAdapter = moshi.adapter(QRResponseData::class.java)
//
//                    val qrResponseData: QRResponseData? = jsonAdapter.fromJson(response.body()!!)
//
//                    val openAPP = findFirstOpenAppSlot(qrResponseData!!.apps)
//                        ?: return Result.failure(Exception("No open app slots"))
//
//                    val appInfo = IXGAppInfo(
//                        name = openAPP.names.first(),
//                        propertyId = qrResponseData.propertyId.toInt(),
//                        qrCode = qrCode,
//                        appSlotID = openAPP.slot.toInt()
//                    )
//
//                    dataStore.setQRCode(qrCode)
//                    dataStore.setIXGAppInfo(appInfo)
//
//                    Result.success(null)
//                }
//            }
//        } else {
//            Result.failure(Exception("Failed to send QR code"))
//        }
        return Result.success(null)
    }

    private fun findFirstOpenAppSlot(apps: List<ACLAppData>): ACLApp? {
        for(app in apps) {
//            if(app.registrationStatus == "1") {
//                return app
//            }
        }
        return null
    }

}