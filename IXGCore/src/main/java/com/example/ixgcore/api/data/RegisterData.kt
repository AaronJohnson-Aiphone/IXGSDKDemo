package com.example.ixgcore.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
