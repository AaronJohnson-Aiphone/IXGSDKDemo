package com.example.ixgcore.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//QR Request ----------------------------------------------
@JsonClass(generateAdapter = true)
data class QRRequestWrapper(
    @Json(name = "BODY") val wrapper: QRRequestData
)

@JsonClass(generateAdapter = true)
data class QRRequestData (
    @Json(name = "ROOMCODE") val roomCode: String,
    @Json(name = "SID") val sid: String,
    @Json(name = "SYS") val sys: String,
    @Json(name = "SYSVER") val sysver: String
)

//QR Response ----------------------------------------------
@JsonClass(generateAdapter = true)
data class QRResponseData(
    @Json(name = "PROPID")        val propertyId: String = "",
//    @Json(name = "BLDNO")         val buildingNumber: String = "",
//    @Json(name = "BLDNMS")        val buildingNames: List<String> = emptyList(),
//    @Json(name = "HHNO")          val roomNumber: String = "",
//    @Json(name = "HHNMS")         val roomNames: List<String> = emptyList(),
//    @Json(name = "TELNO")         val telephoneNumber: String = "",
//    @Json(name = "ROOMTYPE")      val roomType: Int = -1,
//    @Json(name = "REGIONCODE")    val regionCode: String = "",
//    @Json(name = "TERMLIST")      val apps: List<ACLAppData> = emptyList(),
)

//Register Data ----------------------------------------------

@JsonClass(generateAdapter = true)
data class RegisterData(
    @Json(name = "SID")           val sid: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "OSKIND")        val osKind: String = "",
)

@JsonClass(generateAdapter = true)
data class ACLAppData(
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "TERMNO")        val number: String = "",
    @Json(name = "TERMNMS")       val names: List<String> = emptyList(),
    @Json(name = "REGSTS")        val registrationStatus: Int = -1,
)