package com.example.ixgcore.api

import androidx.annotation.Keep
import com.example.ixgcore.ACLApp
import kotlinx.serialization.*


//QR Request ----------------------------------------------
@Keep
@Serializable
data class QRRequestWrapper(
    @SerialName("BODY")      val qrRequestData: QRRequestData,
)

@Keep
@Serializable
data class QRRequestData(
    @SerialName("ROOMCODE")      val roomCode: String = "",
    @SerialName("SID")           val sid: String = "",
    @SerialName("SYS")           val sys: String = "",
    @SerialName("SYSVER")        val sysver: String = "",
)

//QR Response ----------------------------------------------
@Keep
@Serializable
data class QRResponseDataWrapper(
    @SerialName("BODY") val qrResponseData: QRResponseData
)
@Keep
@Serializable
data class QRResponseData(
    @SerialName("PROPID")        val propertyId: String = "",
    @SerialName("BLDNO")         val buildingNumber: String = "",
    @SerialName("BLDNMS")        val buildingNames: List<String> = emptyList(),
    @SerialName("HHNO")          val roomNumber: String = "",
    @SerialName("HHNMS")         val roomNames: List<String> = emptyList(),
    @SerialName("TELNO")         val telephoneNumber: String = "",
    @SerialName("ROOMTYPE")      val roomType: Int = -1,
    @SerialName("REGIONCODE")    val regionCode: String = "",
    @SerialName("TERMLIST")      val apps: List<ACLApp> = emptyList(),
)

//Register Data ----------------------------------------------
@Keep
@Serializable
data class RegisterData(
    @SerialName("SID")           val sid: String = "",
    @SerialName("SYS")           val sys: String = "",
    @SerialName("SYSVER")        val sysver: String = "",
    @SerialName("ROOMCODE")      val roomCode: String = "",
    @SerialName("PROPID")        val propertyId: String = "",
    @SerialName("CLIID")         val clientId: String = "",
    @SerialName("OSKIND")        val osKind: String = "",
)