package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class DeregisterRequestDataWrapper(
    @Json(name = "BODY")           val sid: DeregisterRequestData,
)

data class DeregisterRequestData(
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "SID")           val sid: String = "",
)

data class DeregisterResponseData(
    @Json(name = "message")       val message: String = "",
)
