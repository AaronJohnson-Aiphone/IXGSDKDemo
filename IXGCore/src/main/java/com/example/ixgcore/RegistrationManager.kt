package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.IIXGAPIService
import com.example.ixgcore.api.IXGAPIConstants
import com.example.ixgcore.api.IXGAPIRoute
import com.example.ixgcore.api.QRRequestData
import com.example.ixgcore.api.QRRequestWrapper
import com.example.ixgcore.api.QRResponseData
import com.example.ixgcore.datastore.IIXGSDKDataStore
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistrationManager @Inject constructor(
    private val dataStore: IIXGSDKDataStore
) : IRegistrationManager {
    private val constants = IXGAPIConstants()
    private val apiService = IIXGAPIService.create(constants.defaultServerURl)

    override suspend fun sendQRCode(qrCode: String): Result<Nothing?> {
        val qrData = QRRequestData(roomCode = qrCode, sid = constants.getSidFromDate(), sys = constants.sys, sysver = constants.sysver)
        val qrWrapper = QRRequestWrapper(qrRequestData = qrData)
        val response = apiService.sendQRCode(dataStore.getServerUrl() + IXGAPIRoute.SEND_QR_CODE.api, qrData)
        return if (response.isSuccessful) {
            when(response.body()) {
                null -> Result.failure(Exception("Failed to send QR code"))
                else -> {
                    Log.d("RegistrationManager", "sendQRCode: ${response.body()}")
                    val json = Json{ isLenient = true; ignoreUnknownKeys = true }
                    val qrResponseData: QRResponseData = json.decodeFromString(response.body()!!)
                    val openAPP = findFirstOpenAppSlot(qrResponseData.apps)
                        ?: return Result.failure(Exception("No open app slots"))

                    val appInfo = IXGAppInfo(
                        name = openAPP.names.first(),
                        propertyId = qrResponseData.propertyId.toInt(),
                        qrCode = qrCode,
                        appSlotID = openAPP.slot.toInt()
                    )

                    dataStore.setQRCode(qrCode)
                    dataStore.setIXGAppInfo(appInfo)

                    Result.success(null)
                }
            }
        } else {
            Result.failure(Exception("Failed to send QR code"))
        }
    }

    private fun findFirstOpenAppSlot(apps: List<ACLApp>): ACLApp? {
        for(app in apps) {
            if(app.registrationStatus == "1") {
                return app
            }
        }
        return null
    }
}