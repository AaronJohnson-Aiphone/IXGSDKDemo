package com.example.ixgcore.data

import com.example.ixgcore.api.data.QRRequestData
import com.example.ixgcore.api.data.QRRequestWrapper
import com.example.ixgcore.api.data.QRResponseData
import com.example.ixgcore.api.data.QRResponseDataWrapper
import org.junit.Test

class QRDataTest {

    @Test
    fun dataToNetwork() {
        val qrRequestData = QRRequestData("roomCode", "sid", "sys", "sysver")
        val qrRequestWrapper = QRRequestWrapper(qrRequestData)

    }

    @Test
    fun networkToData() {
        val qrResponseData = QRResponseData("123", "001",
            listOf("Building01"), "1001", listOf("Residential001"),
            "", 1, "00", listOf())
        val qrResponseDataWrapper = QRResponseDataWrapper(qrResponseData)
    }

}