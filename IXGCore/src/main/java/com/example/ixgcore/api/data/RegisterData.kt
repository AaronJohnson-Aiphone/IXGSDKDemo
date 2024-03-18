package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class RegisterRequestDataWrapper(
    @Json(name = "BODY")           val registerRequestData: RegisterRequestData,
)

data class RegisterRequestData(
    @Json(name = "SID")           val sid: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "OSKIND")        val osKind: String = "",
)

data class RegisterResponseDataWrapper(
    @Json(name = "BODY")           val registerResponseData: RegisterResponseData,
)

data class RegisterResponseData(
    @Json(name = "URL")           val url: String = "",
    @Json(name = "SECKEY")        val secretKey: String = "",
    @Json(name = "CERT")          val cert: String = "",
    @Json(name = "REGCODE")       val registrationCode: String = "",
)
