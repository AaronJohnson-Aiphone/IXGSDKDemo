package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class QRRequestWrapper(
    @Json(name = "BODY") val qrRequestData: QRRequestData
)

data class QRRequestData (
    @Json(name = "ROOMCODE") val roomCode: String,
    @Json(name = "SID") val sid: String,
    @Json(name = "SYS") val sys: String,
    @Json(name = "SYSVER") val sysver: String
)

data class QRResponseDataWrapper(
    @Json(name = "BODY") val qrResponseData: QRResponseData
)

data class QRResponseData(
    @Json(name = "PROPID")        val propertyId: String,
    @Json(name = "BLDNO")         val buildingNumber: String,
    @Json(name = "BLDNMS")        val buildingNames: List<String>,
    @Json(name = "HHNO")          val roomNumber: String,
    @Json(name = "HHNMS")         val roomNames: List<String>,
    @Json(name = "TELNO")         val telephoneNumber: String,
    @Json(name = "ROOMTYPE")      val roomType: Int?,
    @Json(name = "REGIONCODE")    val regionCode: String,
    @Json(name = "TERMLIST")      val apps: List<ACLAppData>,
)
