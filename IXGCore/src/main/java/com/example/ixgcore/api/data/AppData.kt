package com.example.ixgcore.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ACLAppData(
    @Json(name = "CLIID")         val clientId: String = "",
    @Json(name = "TERMNO")        val number: String = "",
    @Json(name = "TERMNMS")       val names: List<String> = emptyList(),
    @Json(name = "REGSTS")        val registrationStatus: Int = -1,
)
