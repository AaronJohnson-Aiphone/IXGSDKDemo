package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class DeregisterRequestWrapper(
    @Json(name = "BODY")           val deregisterRequestData: DeregisterRequestData,
)

data class DeregisterRequestData(
    @Json(name = "ROOMCODE")      val roomCode: String,
    @Json(name = "PROPID")        val propertyId: String,
    @Json(name = "CLIID")         val clientId: String,
    @Json(name = "SYS")           val sys: String,
    @Json(name = "SYSVER")        val sysver: String,
    @Json(name = "SID")           val sid: String,
)

data class DeregisterResponseWrapper(
    @Json(name = "BODY")           val deregisterResponseData: DeregisterResponseData,
)

data class DeregisterResponseData(
    @Json(name = "message")       val message: String,
)
