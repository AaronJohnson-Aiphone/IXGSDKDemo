package com.example.ixgcore.api.data

import com.squareup.moshi.Json


data class RenameRequestDataWrapper(
    @Json(name = "BODY")           val renameRequestData: RenameRequestData,
)

data class RenameRequestData(
    @Json(name = "ROOMCODE")      val roomCode: String = "",
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "TERMNM")        val appName: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SID")           val sid: String = "",
)

data class RenameResponseDataWrapper(
    @Json(name = "BODY")           val renameResponseData: RenameResponseData,
)

data class RenameResponseData(
    @Json(name = "message")       val message: String = "",
)
