package com.example.ixgcore.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


// QR data ----------------------------------------------
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

@JsonClass(generateAdapter = true)
data class QRResponseData(
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "BLDNO")         val buildingNumber: String = "",
    @Json(name = "BLDNMS")        val buildingNames: List<String> = emptyList(),
    @Json(name = "HHNO")          val roomNumber: String = "",
    @Json(name = "HHNMS")         val roomNames: List<String> = emptyList(),
    @Json(name = "TELNO")         val telephoneNumber: String = "",
    @Json(name = "ROOMTYPE")      val roomType: Int = -1,
    @Json(name = "REGIONCODE")    val regionCode: String = "",
    @Json(name = "TERMLIST")      val apps: List<ACLAppData> = emptyList(),
    @Json(name = "message")       val message: String = "",
)

// Register data ----------------------------------------------
@JsonClass(generateAdapter = true)
data class RegisterRequestDataWrapper(
    @Json(name = "BODY")           val sid: RegisterRequestData,
)

@JsonClass(generateAdapter = true)
data class RegisterRequestData(
    @Json(name = "SID")           val sid: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "OSKIND")        val osKind: String = "",
)

@JsonClass(generateAdapter = true)
data class RegisterResponseData(//TODO fill in
    @Json(name = "SID")           val sid: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "OSKIND")        val osKind: String = "",
    @Json(name = "message")       val message: String = "",
)

// rename data ----------------------------------------------
@JsonClass(generateAdapter = true)
data class RenameRequestDataWrapper(
    @Json(name = "BODY")           val sid: RenameRequestData,
)

@JsonClass(generateAdapter = true)
data class RenameRequestData(
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "TERMNM")        val appName: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SID")           val sid: String = "",
)

@JsonClass(generateAdapter = true)
data class RenameResponseData(
    @Json(name = "message")       val message: String = "",
)

// deregister data ----------------------------------------------
@JsonClass(generateAdapter = true)
data class DeregisterRequestDataWrapper(
    @Json(name = "BODY")           val sid: DeregisterRequestData,
)

@JsonClass(generateAdapter = true)
data class DeregisterRequestData(
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "SID")           val sid: String = "",
)

@JsonClass(generateAdapter = true)
data class DeregisterResponseData(
    @Json(name = "message")       val message: String = "",
)

// check consent data ----------------------------------------------
@JsonClass(generateAdapter = true)
data class StatusRequestDataWrapper(
    @Json(name = "BODY")           val sid: StatusRequestData,
)

@JsonClass(generateAdapter = true)
data class StatusRequestData(
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SID")           val sid: String = "",
    @Json(name = "REGCODE")       val registrationCode: String = "",
)

@JsonClass(generateAdapter = true)
data class StatusResponseData(
    @Json(name = "CONSENT")       val consent: String = "",
    @Json(name = "ROOMTYPE")      val roomType: String = "",
    @Json(name = "QRAREAFLAG")    val qrAreaFlag: String = "",
    @Json(name = "MEMONUM")       val memoNumber: String = "",
    @Json(name = "message")       val message: String = "",
)


// App data ----------------------------------------------
@JsonClass(generateAdapter = true)
data class ACLAppData(
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "TERMNO")        val number: String = "",
    @Json(name = "TERMNMS")       val names: List<String> = emptyList(),
    @Json(name = "REGSTS")        val registrationStatus: Int = -1,
)