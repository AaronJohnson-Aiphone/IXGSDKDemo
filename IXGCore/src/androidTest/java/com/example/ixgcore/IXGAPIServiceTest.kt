package com.example.ixgcore

import android.util.Log
import com.example.ixgcore.api.IIXGAPIService
import com.example.ixgcore.api.IXGAPIConstants
import com.example.ixgcore.api.IXGAPIRoute
import com.example.ixgcore.api.QRRequestData
import com.example.ixgcore.api.QRRequestWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Test


class IXGAPIServiceTest {
    private val constants = IXGAPIConstants()
    private val apiService = IIXGAPIService.create(constants.defaultServerURl)
    @Test
    fun sendQRCodeTest() {
        val qrData = QRRequestData(roomCode = "23456789", sid = constants.getSidFromDate(), sys = constants.sys, sysver = constants.sysver)
        val qrWrapper = QRRequestWrapper(qrRequestData = qrData)
        runBlocking {
            val response = apiService.sendQRCode(constants.defaultServerURl + IXGAPIRoute.SEND_QR_CODE.api, qrData)
            Log.d("IXGAPIServiceTest", "send QR code: ${response.code()}")
            Log.d("IXGAPIServiceTest", "send QR body: ${response.body()}")
            assert(!response.isSuccessful)
        }
    }
}