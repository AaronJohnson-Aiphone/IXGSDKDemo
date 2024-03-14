package com.example.ixgcore.api.data

import com.squareup.moshi.Json

data class ACLAppData(
    @Json(name = "CLIID")         val clientID: String = "",
    @Json(name = "TERMNO")        val number: String = "",
    @Json(name = "TERMNMS")       val names: List<String> = emptyList(),
    @Json(name = "REGSTS")        val registrationStatus: Int = -1,
)
