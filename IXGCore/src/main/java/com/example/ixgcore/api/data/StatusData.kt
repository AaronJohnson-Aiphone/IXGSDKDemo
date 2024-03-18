package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class StatusRequestDataWrapper(
    @Json(name = "BODY")           val statusRequestData: StatusRequestData,
)

data class StatusRequestData(
    @Json(name = "PROPID")        val propertyId: String = "",
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "SYSVER")        val sysver: String = "",
    @Json(name = "SYS")           val sys: String = "",
    @Json(name = "SID")           val sid: String = "",
    @Json(name = "REGCODE")       val registrationCode: String = "",
)

data class StatusResponseDataWrapper(
    @Json(name = "BODY")           val statusResponseData: StatusResponseData,
)

data class StatusResponseData(
    @Json(name = "CONSENT")       val consent: String = "",
    @Json(name = "ROOMTYPE")      val roomType: String = "",
    @Json(name = "QRAREAFLAG")    val qrAreaFlag: String = "",
    @Json(name = "MEMONUM")       val memoNumber: String = "",
    @Json(name = "message")       val message: String = "",
)
