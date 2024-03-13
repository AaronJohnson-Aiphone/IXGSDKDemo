package com.example.ixgcore.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
