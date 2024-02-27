package com.example.ixgcore.api

import androidx.annotation.Keep
import com.example.ixgcore.ACLApp
import kotlinx.serialization.*


@Keep
@Serializable
data class QRRequestData(
    @SerialName("ROOMCODE")      val roomCode: String = "",
    @SerialName("SID")           val sid: String = "",
    @SerialName("SYS")           val sys: String = "",
    @SerialName("SYSVER")        val sysver: String = "",
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