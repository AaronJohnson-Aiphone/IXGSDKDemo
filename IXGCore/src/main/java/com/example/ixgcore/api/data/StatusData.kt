package com.example.ixgcore.api.data

import com.squareup.moshi.Json


//checkConsentRoom
data class CheckStatusRequestWrapper(
    @Json(name = "BODY")           val checkStatusRequestData: CheckStatusRequestData,
)

data class CheckStatusRequestData(
    @Json(name = "PROPID")        val propertyId: String,
    @Json(name = "CLIID")         val clientId: String,
    @Json(name = "SYSVER")        val sysver: String,
    @Json(name = "SYS")           val sys: String,
    @Json(name = "SID")           val sid: String,
    @Json(name = "REGCODE")       val registrationCode: String,
)

data class CheckStatusResponseWrapper(
    @Json(name = "BODY")           val checkStatusResponseData: CheckStatusResponseData,
)

data class CheckStatusResponseData(
    @Json(name = "CONSENT")       val consent: String,
    @Json(name = "ROOMTYPE")      val roomType: String,
    @Json(name = "QRAREAFLAG")    val qrAreaFlag: String ,
    @Json(name = "MEMONUM")       val memoNumber: String,
    @Json(name = "message")       val message: String,
)

//setConsentInfo
data class SetStatusRequestWrapper(
    @Json(name = "BODY")           val setStatusRequestData: SetStatusRequestData,
)

data class SetStatusRequestData(//TODO get proper fields
    @Json(name = "PROPID")        val propertyId: String,
    @Json(name = "CLIID")         val clientId: String,
    @Json(name = "HHID")        val sysver: String,
    @Json(name = "SYS")           val sys: String,
    @Json(name = "SID")           val sid: String,
    @Json(name = "REGCODE")       val registrationCode: String,
)

data class SetStatusResponseWrapper(
    @Json(name = "BODY")           val setStatusResponseData: SetStatusResponseData,
)

data class SetStatusResponseData(//TODO get proper fields
    @Json(name = "CONSENT")       val consent: String,
    @Json(name = "ROOMTYPE")      val roomType: String,
    @Json(name = "QRAREAFLAG")    val qrAreaFlag: String,
    @Json(name = "MEMONUM")       val memoNumber: String,
    @Json(name = "message")       val message: String,
)
